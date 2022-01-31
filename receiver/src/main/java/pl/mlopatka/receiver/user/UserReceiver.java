package pl.mlopatka.receiver.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserReceiver {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final UserDataCreator userDataCreator;

    @Transactional
    public void consumeUserMsg(String userToUpdate) throws JsonProcessingException {
        User userMessage = objectMapper.readValue(userToUpdate, User.class);
        User persistentUser = userRepository.findByUsername(userMessage.getUsername());
        userDataCreator.updateUserData(persistentUser);
    }
}
