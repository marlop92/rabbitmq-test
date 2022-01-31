package pl.mlopatka.receiver.user.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mlopatka.receiver.user.UserReceiver;

@Configuration
@RequiredArgsConstructor
public class AmqpConfig {

    private final UserConfig userConfig;
    private static final String USERS_QUEUE = "users";

    @Bean
    TopicExchange usersExchange() {
        return new TopicExchange(userConfig.getExchange());
    }

    @Bean
    Queue usersQueue() {
        return new Queue(USERS_QUEUE, false);
    }

    @Bean
    Binding binding(Queue usersQueue, TopicExchange usersExchange) {
        return BindingBuilder.bind(usersQueue)
                .to(usersExchange)
                .with(userConfig.getRoutingPattern());
    }

    @Bean
    MessageListenerAdapter adapter(UserReceiver userReceiver) {
        return new MessageListenerAdapter(userReceiver, "consumeUserMsg");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory factory, MessageListenerAdapter adapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames(USERS_QUEUE);
        container.setMessageListener(adapter);

        return container;
    }
}
