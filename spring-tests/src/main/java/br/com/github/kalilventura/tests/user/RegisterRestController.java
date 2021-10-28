package br.com.github.kalilventura.tests.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegisterRestController {
    private final RegisterUseCase registerUseCase;

    @PostMapping("/forums/{forumId}/register")
    public UserDTO register(
            @PathVariable("forumId") Long forumId,
            @Valid @RequestBody UserDTO userResource,
            @RequestParam("sendWelcomeMail") boolean sendWelcomeMail) {

        User user = new User(userResource.getName(), userResource.getEmail());
        Long userId = registerUseCase.registerUser(user, sendWelcomeMail);

        return new UserDTO(userId, user.getName(), user.getEmail());
    }
}
