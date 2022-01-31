package pl.mlopatka.receiver.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static pl.mlopatka.receiver.user.config.AmqpConfig.USERS_QUEUE;

@Component
@RequiredArgsConstructor
public class UserReceiver {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final UserDataCreator userDataCreator;

    @Transactional
    @RabbitListener(queues = {USERS_QUEUE})
    public void consumeUserMsg(String userToUpdate) throws JsonProcessingException {
        User userMessage = objectMapper.readValue(userToUpdate, User.class);
        User persistentUser = userRepository.findByUsername(userMessage.getUsername());
        userDataCreator.updateUserData(persistentUser);
    }
}
