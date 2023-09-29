package pl.maciejak.charity.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.service.InstitutionService;
import pl.maciejak.charity.service.UserService;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    private final UserService userService;
    private final InstitutionService institutionService;

    public DashboardController(UserService userService, InstitutionService institutionService) {
        this.userService = userService;
        this.institutionService = institutionService;
    }

    @GetMapping("/dashboard")
    public String users(Model model) {
        model.addAttribute("users", userService.findByRoles("ROLE_USER"));
        return "admin/dashboard";
    }

    @GetMapping("/admins")
    public String admins(Model model) {
        model.addAttribute("admins", userService.findByRoles("ROLE_ADMIN"));
        return "admin/admins/list";
    }

    @GetMapping("/institutions")
    public String institutions(Model model) {
        model.addAttribute("institutions", institutionService.findAll());
        return "admin/institution/list";
    }
}