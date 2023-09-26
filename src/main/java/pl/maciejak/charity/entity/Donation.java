package pl.maciejak.charity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Table(name = "donations")
@Entity
@Getter
@Setter
@ToString
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Positive
    @NotNull
    private Long quantity;

    @NotEmpty
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Category> categories;

    @NotNull
    @ManyToOne
    private Institution institution;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    @Length(max = 255)
    private String pickUpComment;

    @NotBlank
    private String phoneNumber;

    @ManyToOne
    private User user;

    private boolean isPickedUp;
    private boolean isArchived;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickedUpDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickedUpTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    @PrePersist
    private void prePersist() {
        this.created = LocalDateTime.now();
    }
}