package pl.maciejak.charity.service.interfaces;

import pl.maciejak.charity.entity.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> findByRoles(String roleName, int pageNumber);

    User findByUsername(String email);

    User findById(Long id);

    void save(User user);

    void delete(User user);

    void updatePassword(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
