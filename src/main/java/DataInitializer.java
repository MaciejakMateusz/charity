import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.UserService;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    @Value("${admin.email}")
    String adminEmail;
    @Value("${admin.password}")
    private String adminPassword;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        if(!userService.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setPassword(adminPassword);
            userService.saveAdmin(admin);
        }
    }
}