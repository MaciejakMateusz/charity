package pl.maciejak.charity.controller.profile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
    public String profile(Model model, HttpSession session) {
        log.info((String) session.getAttribute("user"));
        model.addAttribute("user", new User());
        return "profile/profile";
    }

    @PostMapping
    public String profile(@Valid User user, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "profile/profile";
        }
        model.addAttribute("formSubmitted", true);
        userService.saveUser(user);
        return "profile/profile";
    }
}