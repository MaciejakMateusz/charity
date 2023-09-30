package pl.maciejak.charity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.repository.RoleRepository;
import pl.maciejak.charity.repository.UserRepository;
import pl.maciejak.charity.service.interfaces.UserServiceInterface;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<User> findByRoles(String roleName, Pageable pageable) {
        return userRepository.findByRoleName(roleName, pageable);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public void save(User user) {
        user.setEnabled(1);
        user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName("ROLE_USER"))));
        userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void updatePassword(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        save(user);
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