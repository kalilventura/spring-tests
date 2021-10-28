package br.com.github.kalilventura.tests.user;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterUseCase {
    private final UserRepository userRepository;
    private final Mailing sendMail;

    public RegisterUseCase(UserRepository userRepository, Mailing sendMail) {
        this.userRepository = userRepository;
        this.sendMail = sendMail;
    }


    public User registerUser(User user) {
        user.setRegistrationDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Long registerUser(User user, boolean sendWelcomeMail) {
        user.setRegistrationDate(LocalDateTime.now());

        if (sendWelcomeMail)
            sendMail.sendMail("Welcome!", "Thanks for registering.");

        return userRepository.save(user).getId();
    }

}

