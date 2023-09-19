package pl.maciejak.charity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "institutions")
@Entity
@Getter
@Setter
@ToString
public class Institution {
    @Id
    private Integer id;
    private String name;
    private String description;
}
