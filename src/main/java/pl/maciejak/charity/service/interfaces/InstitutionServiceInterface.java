package pl.maciejak.charity.service.interfaces;

import pl.maciejak.charity.entity.Institution;

import java.util.List;

public interface InstitutionServiceInterface {
    Institution findById(Integer id);
    List<Institution> findAll();
    void save(Institution institution);
    void delete(Institution institution);
}
