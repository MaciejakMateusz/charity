package pl.maciejak.charity.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.service.UserService;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String form(Model model) {
        model.addAttribute("users", userService.findByRoles("ROLE_USER"));
        return "admin/dashboard";
    }
}