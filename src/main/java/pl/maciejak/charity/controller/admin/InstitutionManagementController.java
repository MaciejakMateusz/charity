package pl.maciejak.charity.controller.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.maciejak.charity.entity.Institution;
import pl.maciejak.charity.service.InstitutionService;

@Controller
@RequestMapping("/admin/institution")
public class InstitutionManagementController {

    private final InstitutionService institutionService;

    public InstitutionManagementController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    private Institution institution;

    @PostMapping("/show")
    public String show(@RequestParam Long id, Model model) {
        this.institution = institutionService.findById(id);
        model.addAttribute("institution", this.institution);
        return "admin/institution/show";
    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("institution", this.institution);
        return "admin/institution/show";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        this.institution = institutionService.findById(id);
        model.addAttribute("institution", this.institution);
        return "admin/institution/edit";
    }

    @PostMapping("/update")
    public String edit(@Valid Institution institution, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "admin/institution/edit";
        }
        institutionService.save(institution);
        model.addAttribute("isUpdated", true);
        return "admin/institution/edit";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("institution", this.institution);
        return "admin/institution/edit";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        this.institution = institutionService.findById(id);
        model.addAttribute("institution", this.institution);
        return "admin/institution/delete";
    }

    @PostMapping("/remove")
    public String delete(Institution institution, Model model) {
        institutionService.delete(institution);
        model.addAttribute("isRemoved", true);
        return "admin/institution/delete";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("institution", this.institution);
        return "admin/institution/delete";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/institution/add";
    }

    @PostMapping("/add")
    public String add(@Valid Institution institution, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "admin/institution/add";
        }
        institutionService.save(institution);
        model.addAttribute("isCreated", true);
        model.addAttribute("institution", new Institution());
        return "admin/institution/add";
    }

}