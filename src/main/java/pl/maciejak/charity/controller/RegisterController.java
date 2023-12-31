package pl.maciejak.charity.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.EmailService;
import pl.maciejak.charity.service.UserService;

import java.util.Objects;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final EmailService emailService;

    public RegisterController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String register(@Valid User user, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "register";
        } else if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("emailExists", true);
            return "register";
        } else if (!user.getPassword().equals(user.getRepeatedPassword())) {
            model.addAttribute("passwordsNotMatch", true);
            return "register";
        }
        userService.saveTemporary(user);
        emailService.activateAccount(user.getEmail());
        model.addAttribute("activationSent", true);
        model.addAttribute("email", user.getEmail());
        return "register";
    }

    @GetMapping("/{token}")
    public String activate(@PathVariable String token, Model model) {
        User foundUser = userService.findUserByToken(token).orElse(null);
        if (Objects.isNull(foundUser)) {
            model.addAttribute("error", true);
            return "register";
        }
        foundUser.setToken(null);
        userService.save(foundUser);
        return "success-registration";
    }

}
