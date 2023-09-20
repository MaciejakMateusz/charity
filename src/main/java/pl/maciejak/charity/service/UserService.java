package pl.maciejak.charity.service;

import org.springframework.stereotype.Service;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.repository.RoleRepository;
import pl.maciejak.charity.repository.UserRepository;
import pl.maciejak.charity.service.interfaces.UserServiceInterface;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setEnabled(1);
        user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName("ROLE_USER"))));
        userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}