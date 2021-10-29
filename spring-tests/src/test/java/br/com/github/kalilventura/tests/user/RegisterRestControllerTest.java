package br.com.github.kalilventura.tests.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegisterRestController.class)
class RegisterRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegisterUseCase registerUseCase;

    @Test
    void whenValidInputThenReturns200() throws Exception {
        UserDTO userResource = new UserDTO("John", "john@gmail.com");

        User addUserResponse = new User(userResource.getName(), userResource.getEmail());

        //mocking the bean for any object of AddUserRequest.class
        when(registerUseCase.registerUser(any(User.class))).thenReturn(addUserResponse);

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(userResource)))
                .andExpect(status().isOk());
    }
}
