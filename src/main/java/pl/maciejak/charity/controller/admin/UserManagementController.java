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
        return "admin/manage/show";
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("user", this.user);
        return "admin/manage/show";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        this.user = userService.findById(id);
        model.addAttribute("user", this.user);
        return "admin/manage/edit";
    }

    @PostMapping("/update")
    public String edit(@Valid User user, BindingResult br, Model model) {
        if(br.hasErrors()) {
            return "admin/manage/edit";
        }
        userService.save(user);
        model.addAttribute("isUpdated", true);
        return "admin/manage/edit";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("user", this.user);
        return "admin/manage/edit";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        this.user = userService.findById(id);
        model.addAttribute("user", this.user);
        return "admin/manage/delete";
    }

    @PostMapping("/remove")
    public String delete(@Valid User user, BindingResult br, Model model) {
        if(br.hasErrors()) {
            return "admin/manage/delete";
        }
        userService.delete(user);
        model.addAttribute("isRemoved", true);
        return "admin/manage/delete";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("user", this.user);
        return "admin/manage/delete";
    }
}