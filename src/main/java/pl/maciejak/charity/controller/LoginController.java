package pl.maciejak.charity.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.EmailService;
import pl.maciejak.charity.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final EmailService emailService;

    public LoginController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/password-recovery")
    public String recovery() {
        return "password-recovery";
    }

    @PostMapping("/password-recovery")
    public String recovery(@RequestParam String email, Model model) {
        if (!userService.existsByEmail(email)) {
            model.addAttribute("userExists", false);
            return "password-recovery";
        }
        emailService.passwordRecovery(email);
        model.addAttribute("emailSent", true);
        model.addAttribute("email", email);
        return "password-recovery";
    }

    @GetMapping("/{recoveryToken}")
    public String resetPassword(@PathVariable String recoveryToken, Model model) {

        Map<User, String> usersRecoveryTokens = emailService.getUsersRecoveryTokens();
        User foundUser = new User();
        boolean isFound = false;
        for (Map.Entry<User, String> entry : usersRecoveryTokens.entrySet()) {
            if (entry.getValue().equals(recoveryToken)) {
                foundUser = entry.getKey();
                isFound = true;
                usersRecoveryTokens.remove(foundUser, recoveryToken);
                emailService.setUsersRecoveryTokens(usersRecoveryTokens);
                break;
            }
        }

        if (isFound) {
            model.addAttribute("user", foundUser);
            return "reset-password";
        } else {
            model.addAttribute("error", true);
            return "password-recovery";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@Valid User user, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "reset-password";
        } else if (!user.getPassword().equals(user.getRepeatedPassword())) {
            model.addAttribute("passwordsNotMatch", true);
            return "reset-password";
        }
        userService.updatePassword(user);
        model.addAttribute("isReset", true);
        return "reset-password";
    }

}
