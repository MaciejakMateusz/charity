package pl.maciejak.charity.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.interfaces.EmailServiceInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Getter
@Setter
public class EmailService implements EmailServiceInterface {

    private final JavaMailSender emailSender;
    private final UserService userService;
    private static final String LOCAL_HOST = "http://localhost:8080/login/";
    private Map<User, String> usersRecoveryTokens = new HashMap<>();

    public EmailService(JavaMailSender emailSender,
                        UserService userService) {
        this.emailSender = emailSender;
        this.userService = userService;
    }

    @Override
    public void contactForm(String from, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo("applicationcharity@gmail.com");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void passwordRecovery(String to) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@charityapp.pl");
        message.setTo(to);
        message.setSubject("Charity - jednorazowy link do zmiany hasła");

        User user = userService.findByUsername(to);
        UUID token = UUID.randomUUID();
        String recoveryToken = token.toString();
        this.usersRecoveryTokens.put(user, recoveryToken);

        String link = LOCAL_HOST + recoveryToken;
        message.setText("Twój link do zmiany hasła: " + link);

        emailSender.send(message);
    }
}