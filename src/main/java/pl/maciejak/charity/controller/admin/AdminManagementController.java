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
@RequestMapping("/admin/admins")
public class AdminManagementController {

    private final UserService userService;

    public AdminManagementController(UserService userService) {
        this.userService = userService;
    }

    private User admin;

    @PostMapping("/show")
    public String show(@RequestParam Long id, Model model) {
        this.admin = userService.findById(id);
        model.addAttribute("admin", this.admin);
        return "admin/admins/show";
    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("admin", this.admin);
        return "admin/admins/show";
    }
}