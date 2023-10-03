package pl.maciejak.charity.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.EmailService;
import pl.maciejak.charity.service.UserService;

import java.util.Objects;

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
        User foundUser = userService.findUserByToken(recoveryToken).orElse(null);
        if (Objects.isNull(foundUser)) {
            model.addAttribute("error", true);
            return "password-recovery";
        }
        model.addAttribute("user", foundUser);
        return "reset-password";
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
