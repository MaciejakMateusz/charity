package pl.maciejak.charity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import pl.maciejak.charity.annotation.Email;

import java.time.LocalDateTime;

@Table(name = "institutions")
@Entity
@Getter
@Setter
@ToString
@Slf4j
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String street,
            city,
            zipCode,
            phoneNumber;

    @Email
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updated;

    @PrePersist
    private void prePersist() {

        this.name = this.name.replace("\"", "");
        this.name = "\"" + this.name + "\"";

        this.created = LocalDateTime.now();
        log.info("Creation date has been set to : " + this.created);
    }

    @PreUpdate
    private void preUpdate() {

        this.name = this.name.replace("\"", "");
        this.name = "\"" + this.name + "\"";

        this.updated = LocalDateTime.now();
        log.info("Date of edition has been set to : " + this.updated);
    }
}
