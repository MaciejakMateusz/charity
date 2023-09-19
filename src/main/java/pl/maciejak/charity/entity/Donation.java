package pl.maciejak.charity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Table(name = "dontations")
@Entity
@Getter
@Setter
@ToString
public class Donation {
    @Id
    private Integer id;
    private Integer quantity;
    @OneToMany
    private List<Category> categories;
    @ManyToOne
    private Institution institution;
    private String street;
    private String city;
    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
}
