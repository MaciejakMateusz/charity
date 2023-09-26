package pl.maciejak.charity.controller.profile;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.UserService;

@Controller
@RequestMapping("/profile")
@Slf4j
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile/profile";
    }

    @PostMapping
    public String profile(User user, Model model) {
        userService.save(user);
        model.addAttribute("formSubmitted", true);
        return "profile/profile";
    }

    @GetMapping("/password")
    public String password(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile/password/password";
    }

    @PostMapping("/password")
    public String password(@Valid User user, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "profile/password/password";
        }
        if (!user.getPassword().equals(user.getRepeatedPassword())) {
            model.addAttribute("passwordsNotMatch", true);
            return "profile/password/password";
        }
        userService.updatePassword(user);
        model.addAttribute("formSubmitted", true);
        return "profile/password/password";
    }
}