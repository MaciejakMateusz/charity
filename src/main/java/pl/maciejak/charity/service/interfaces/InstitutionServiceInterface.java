package pl.maciejak.charity.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.maciejak.charity.entity.Institution;

import java.util.List;

public interface InstitutionServiceInterface {
    Institution findById(Long id);

    Page<Institution> findAll(Pageable pageable);

    List<Institution> findAll();

    void save(Institution institution);

    void delete(Institution institution);
}
