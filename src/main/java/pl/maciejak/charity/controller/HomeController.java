package pl.maciejak.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.entity.Institution;
import pl.maciejak.charity.repository.InstitutionRepository;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final InstitutionRepository institutionRepository;

    public HomeController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @ModelAttribute("institutions")
    private List<Institution> getInstitutions() {
        return institutionRepository.findAll();
    }

}