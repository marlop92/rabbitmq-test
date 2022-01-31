package pl.mlopatka.receiver.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class UserReceiver {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Transactional
    @RabbitListener
    public void consumeUserMsg(String userToUpdate) throws JsonProcessingException {
        User userMessage = objectMapper.readValue(userToUpdate, User.class);
        User persistentUser = userRepository.findByUsername(userMessage.getUsername());
        persistentUser.setAge(24);
        persistentUser.setFavouriteDish("Fish&Chips");
        persistentUser.setFavouriteMovie("Once Upon a Time in Hollywood");
        persistentUser.setLastUpdateTime(ZonedDateTime.now());
    }
}
