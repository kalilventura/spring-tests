package br.com.github.kalilventura.tests.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterUseCaseTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private Mailing mailing;
    @InjectMocks
    private RegisterUseCase registerUseCase;

    @Test
    void saveUserHasRegistrationDate() {
        User user = new User("John", "john@mail.com");

        when(userRepository.save(user)).then(returnsFirstArg());

        User savedUser = registerUseCase.registerUser(user);
        assertThat(savedUser.getRegistrationDate()).isNotNull();
    }

    @Test
    void doNotSendEmailWhenSendWelcomeMailIsFalse() {
        User user = new User("John", "john@mail.com");

        when(userRepository.save(user)).thenReturn(user);

        registerUseCase.registerUser(user, false);

        verify(mailing, never()).sendMail(anyString(), anyString());
    }

    @Test
    void sendEmailWhenSendWelcomeMailIsTrue() {
        User user = new User("John", "john@mail.com");

        when(userRepository.save(user)).thenReturn(user);

        registerUseCase.registerUser(user, true);

        verify(mailing, atMostOnce()).sendMail(anyString(), anyString());
    }

}