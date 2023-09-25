package pl.maciejak.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejak.charity.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
