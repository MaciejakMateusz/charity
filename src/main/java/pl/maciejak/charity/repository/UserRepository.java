package pl.maciejak.charity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.maciejak.charity.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    Page<User> findByRoleName(@Param("roleName") String roleName, Pageable pageable);

    @Query("SELECT u FROM User u " +
            "JOIN u.roles r ON r.name = :roleName " +
            "WHERE u.email LIKE %:partialEmail% " +
            "AND r.name = :roleName")
    Page<User> findUserByEmailContainsAndRoleName(String partialEmail, String roleName, Pageable pageable);

    @Query("SELECT u FROM User u " +
            "JOIN u.roles r " +
            "WHERE u.id = :id " +
            "AND r.name = :roleName")
    Optional<User> findUserByIdAndRoleName(Long id, String roleName);
}
