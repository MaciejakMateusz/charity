package pl.maciejak.charity.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.maciejak.charity.entity.User;

import java.util.Optional;

public interface UserServiceInterface {
    Page<User> findByRoles(String roleName, Pageable pageable);

    Page<User> findByPartialEmail(String email, String roleName, Pageable pageable);

    User findByUsername(String email);

    User findById(Long id);

    User findByIdAndRoleName(Long id, String roleName);

    Optional<User> findUserByToken(String token);

    void save(User user);

    void update(User user);

    void saveTemporary(User user);

    void block(User user);

    void delete(User user);

    void updatePassword(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsById(Long id);
}
