package pl.maciejak.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.entity.User;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
}
