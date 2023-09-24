package pl.maciejak.charity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import pl.maciejak.charity.annotation.Email;
import pl.maciejak.charity.annotation.Password;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
@Slf4j
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 150)
    private String username;

    @Column(length = 100, nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Password
    private String password;

    private String
            name,
            surname,
            street,
            city,
            zipCode,
            phoneNumber;

    @Transient
    private String repeatedPassword;

    private LocalDateTime created;
    private LocalDateTime updated;

    private int enabled;

    @OneToMany
    private List<Donation> donations;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @PrePersist
    private void prePersist() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
        log.info("Password has been successfully encrypted.");
        this.created = LocalDateTime.now();
        log.info("Creation date has been set to : " + this.created);
        setUsername(this.email);
        log.info("User's username has been set to: " + this.email);
    }

    @PreUpdate
    private void preUpdate() {
        this.updated = LocalDateTime.now();
        log.info("Date of edition has been set to : " + this.updated);
    }
}