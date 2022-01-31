package pl.mlopatka.receiver.user.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AmqpConfig {

    private final UserConfig userConfig;
    public static final String USERS_QUEUE = "users";

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

}
