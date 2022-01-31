package pl.mlopatka.sender.sender.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/{username}")
    public ResponseEntity<Void> createUser(@PathVariable("username") String username) throws JsonProcessingException {
        userService.createUser(username);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
