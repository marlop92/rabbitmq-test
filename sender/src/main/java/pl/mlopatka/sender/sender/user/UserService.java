package pl.mlopatka.sender.sender.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mlopatka.sender.sender.user.config.UserConfig;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserConfig userConfig;
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final UserRepository userRepository;

    public void createUser(String username) throws JsonProcessingException {
        User user = new User();
        user.setCreationTime(ZonedDateTime.now());
        user.setUsername(username);

        userRepository.save(user);
        rabbitTemplate.convertAndSend(userConfig.getExchange(), userConfig.getRoutingKey(),
                objectMapper.writeValueAsString(user));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
