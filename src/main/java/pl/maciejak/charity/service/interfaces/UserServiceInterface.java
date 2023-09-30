package pl.maciejak.charity.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.maciejak.charity.entity.User;

import java.util.List;

public interface UserServiceInterface {
    Page<User> findByRoles(String roleName, Pageable pageable);

    User findByUsername(String email);

    User findById(Long id);

    void save(User user);

    void delete(User user);

    void updatePassword(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
