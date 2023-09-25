package pl.maciejak.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejak.charity.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
