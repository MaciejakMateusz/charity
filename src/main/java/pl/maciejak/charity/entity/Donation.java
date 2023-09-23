package pl.maciejak.charity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @NotBlank
    @Positive
    private Integer quantity;

    @NotBlank
    @ManyToMany
    private List<Category> categories;

    @NotBlank
    @ManyToOne
    private Institution institution;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @NotBlank
    private LocalDate pickUpDate;

    @NotBlank
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    @NotBlank
    private String pickUpComment;

    @NotBlank
    private String phoneNumber;
}