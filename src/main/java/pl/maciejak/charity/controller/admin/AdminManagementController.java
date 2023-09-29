package pl.maciejak.charity.controller.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @PostMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        this.admin = userService.findById(id);
        model.addAttribute("admin", this.admin);
        return "admin/admins/edit";
    }

    @PostMapping("/update")
    public String edit(@Valid User admin, BindingResult br, Model model) {
        if(br.hasErrors()) {
            return "admin/admins/edit";
        }
        userService.save(admin);
        model.addAttribute("isUpdated", true);
        return "admin/admins/edit";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("admin", this.admin);
        return "admin/admins/edit";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("admin", new User());
        return "admin/admins/add";
    }

    @PostMapping("/add")
    public String add(@Valid User admin, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "admin/admins/add";
        } else if (userService.existsByEmail(admin.getEmail())) {
            model.addAttribute("emailExists", true);
            return "admin/admins/add";
        } else if (!admin.getPassword().equals(admin.getRepeatedPassword())) {
            model.addAttribute("passwordsNotMatch", true);
            return "admin/admins/add";
        }
        userService.save(admin);
        model.addAttribute("isCreated", true);
        model.addAttribute("admin", new User());
        return "admin/admins/add";
    }

}