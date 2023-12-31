package pl.maciejak.charity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejak.charity.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Page<Institution> findInstitutionByEmailContains(String partialEmail, Pageable pageable);
}
