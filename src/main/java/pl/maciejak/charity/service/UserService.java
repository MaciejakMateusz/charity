package pl.maciejak.charity.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.repository.RoleRepository;
import pl.maciejak.charity.repository.UserRepository;
import pl.maciejak.charity.service.interfaces.UserServiceInterface;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

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
    public Page<User> findByPartialEmail(String email, String roleName, Pageable pageable) {
        return userRepository.findUserByEmailContainsAndRoleName(email, roleName, pageable);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    @Override
    public User findByIdAndRoleName(Long id, String roleName) {
        return userRepository.findUserByIdAndRoleName(id, roleName).orElse(null);
    }

    @Override
    public Optional<User> findUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    @Override
    public void save(User user) {
        user.setEnabled(1);
        user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName("ROLE_USER"))));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void saveAdmin(String email, String password) {
        User admin = new User();
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setEnabled(1);
        admin.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName("ROLE_ADMIN"))));
        userRepository.save(admin);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveTemporary(User user) {
        user.setEnabled(0);
        userRepository.save(user);
    }

    @Override
    public void block(User user) {
        if (user.getEnabled() == 0) {
            user.setEnabled(1);
        } else {
            user.setEnabled(0);
        }
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void updatePassword(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

}