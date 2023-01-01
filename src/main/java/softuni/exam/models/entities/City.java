package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Length(min = 2, max = 60)
    private String cityName;

    @Lob
    @Length(min = 2)
    private String description;

    @NotNull
    @Min(500)
    private int population;

    @ManyToOne
    private Country country;

    public City(String cityName, String description, int population, Country country) {
        this.cityName = cityName;
        this.description = description;
        this.population = population;
        this.country = country;
    }

    public City() {

    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDescription() {
        return description;
    }

    public int getPopulation() {
        return population;
    }

    public Country getCountry() {
        return country;
    }
}
