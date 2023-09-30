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
        return "admin/user/show";
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("user", this.user);
        return "admin/user/show";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        this.user = userService.findById(id);
        model.addAttribute("user", this.user);
        return "admin/user/edit";
    }

    @PostMapping("/update")
    public String edit(@Valid User user, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "admin/user/edit";
        }
        userService.save(user);
        model.addAttribute("isUpdated", true);
        return "admin/user/edit";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("user", this.user);
        return "admin/user/edit";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        this.user = userService.findById(id);
        model.addAttribute("user", this.user);
        return "admin/user/delete";
    }

    @PostMapping("/remove")
    public String delete(User user, Model model) {
        userService.delete(user);
        model.addAttribute("isRemoved", true);
        return "admin/user/delete";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("user", this.user);
        return "admin/user/delete";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/add";
    }

    @PostMapping("/add")
    public String add(@Valid User user, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "admin/user/add";
        } else if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("emailExists", true);
            return "admin/user/add";
        } else if (!user.getPassword().equals(user.getRepeatedPassword())) {
            model.addAttribute("passwordsNotMatch", true);
            return "admin/user/add";
        }
        userService.save(user);
        model.addAttribute("isCreated", true);
        model.addAttribute("user", new User());
        return "admin/user/add";
    }

    @PostMapping("/block")
    public String block(@RequestParam Long id, Model model) {
        User user = userService.findById(id);
        userService.block(user);
        model.addAttribute("user", user);
        return "admin/user/show";
    }
}