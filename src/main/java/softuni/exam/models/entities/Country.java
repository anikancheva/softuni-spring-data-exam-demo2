package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Length(min = 2, max = 60)
    private String countryName;

    @NotNull
    @Length(min = 2, max = 60)
    private String currency;

    public Long getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrency() {
        return currency;
    }
}
