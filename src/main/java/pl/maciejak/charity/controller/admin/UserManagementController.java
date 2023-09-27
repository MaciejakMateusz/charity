package pl.maciejak.charity.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserManagementController {

    private final UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    private User user;

    @PostMapping
    public String show(@RequestParam Long id, Model model) {
        this.user = userService.findById(id);
        model.addAttribute("user", this.user);
        return "admin/show";
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("user", this.user);
        return "admin/show";
    }

}