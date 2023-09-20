package pl.maciejak.charity.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
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
        } else if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("usernameExists", true);
            return "register";
        } else if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("emailExists", true);
            return "register";
        } else if (!user.getPassword().equals(user.getRepeatedPassword())) {
            model.addAttribute("passwordsNotMatch", true);
            return "register";
        }
        userService.saveUser(user);
        return "success-registration";
    }
}
