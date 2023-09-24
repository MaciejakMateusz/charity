package pl.maciejak.charity.service.interfaces;

import pl.maciejak.charity.entity.User;

public interface UserServiceInterface {
    User findByUsername(String email);

    void save(User user);

    void updatePassword(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
