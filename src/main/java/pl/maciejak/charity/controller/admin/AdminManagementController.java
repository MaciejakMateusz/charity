package pl.maciejak.charity.controller.admin;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin/admins")
public class AdminManagementController {

    private User admin;
    private static final int PAGE_SIZE = 10;
    private Pageable pageable = PageRequest.of(0, PAGE_SIZE);
    private Page<User> allAdmins;
    private User foundAdmin;
    private boolean filterEngaged;
    private String emailFilter;

    private final UserService userService;
    public AdminManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String admins(Model model) {
        this.filterEngaged = false;
        this.allAdmins = getAllAdmins();
        int totalPages = allAdmins.getTotalPages();
        model.addAttribute("pageable", this.pageable);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("admins", allAdmins.stream().toList());
        return "admin/admins/list";
    }

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
        if (br.hasErrors()) {
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

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        this.admin = userService.findById(id);
        model.addAttribute("admin", this.admin);
        return "admin/admins/delete";
    }

    @PostMapping("/remove")
    public String delete(User admin, Model model, Principal principal) {
        User currentAdmin = userService.findByUsername(principal.getName());
        if (currentAdmin.getId() == admin.getId()) {
            return "admin/admins/delete";
        }
        userService.delete(admin);
        model.addAttribute("isRemoved", true);
        return "admin/admins/delete";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("admin", this.admin);
        return "admin/admins/delete";
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

    @PostMapping("/page")
    private String setPageNumber(@RequestParam int pageNumber) {
        if (pageNumber >= 0) {
            this.pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        }
        if(filterEngaged) {
            return "redirect:/admin/admins/findByEmail";
        }
        return "redirect:/admin/admins";
    }

    @PostMapping("/incrementPage")
    private String incrementPageNumber() {
        if (allAdmins.hasNext()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() + 1, PAGE_SIZE);
        }
        if(filterEngaged) {
            return "redirect:/admin/admins/findByEmail";
        }
        return "redirect:/admin/admins";
    }

    @PostMapping("/decrementPage")
    private String decrementPage() {
        if (this.pageable.hasPrevious()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() - 1, PAGE_SIZE);
        }
        if(filterEngaged) {
            return "redirect:/admin/admins/findByEmail";
        }
        return "redirect:/admin/admins";
    }

    @PostMapping("/findById")
    private String findById(@RequestParam Long id, Model model) {
        model.addAttribute("filterEngaged", true);
        if (!userService.existsById(id)) {
            this.foundAdmin = null;
            model.addAttribute("userNotFound", true);
        } else {
            this.foundAdmin = userService.findById(id);
            model.addAttribute("foundUser", foundAdmin);
        }
        return "admin/admins/list";
    }

    @GetMapping("/findById")
    private String findById(Model model) {
        model.addAttribute("filterEngaged", true);
        if (this.foundAdmin == null) {
            model.addAttribute("userNotFound", true);
        } else {
            model.addAttribute("foundUser", this.foundAdmin);
        }
        return "admin/admins/list";
    }

    @PostMapping("/findByEmail")
    private String findByEmail(@RequestParam String email, Model model) {
        this.filterEngaged = true;
        this.emailFilter = email;
        this.allAdmins = userService.findByPartialEmail(email, this.pageable);
        if (this.allAdmins == null || this.allAdmins.isEmpty()) {
            model.addAttribute("userNotFound", true);
        } else {
            emailFilterModelAttributes(model);
        }
        return "admin/admins/list";
    }

    @GetMapping("/findByEmail")
    private String findByEmail(Model model) {
        filterEngaged = true;
        this.allAdmins = userService.findByPartialEmail(this.emailFilter, this.pageable);
        if (this.allAdmins.isEmpty()) {
            model.addAttribute("userNotFound", true);
        } else {
            emailFilterModelAttributes(model);
        }
        return "admin/admins/list";
    }

    private void emailFilterModelAttributes(Model model) {
        int totalPages = this.allAdmins.getTotalPages();
        model.addAttribute("pageable", this.pageable);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("foundByEmail", true);
        model.addAttribute("partialEmail", this.emailFilter);
        model.addAttribute("admins", this.allAdmins.stream().toList());
    }

    private Page<User> getAllAdmins() {
        return userService.findByRoles("ROLE_ADMIN", pageable);
    }
}