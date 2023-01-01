package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "forecasts",
        uniqueConstraints = @UniqueConstraint(name = "dayOfWeek_city", columnNames = {"day_of_week", "city_id"}))
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @NotNull
    @Range(min = -20, max = 60)
    private double maxTemperature;

    @NotNull
    @Range(min = -50, max = 40)
    private double minTemperature;

    @NotNull
    private LocalTime sunrise;

    @NotNull
    private LocalTime sunset;

    @NotNull
    @ManyToOne
    private City city;

    public Forecast(DayOfWeek dayOfWeek, double maxTemperature, double minTemperature, LocalTime sunrise, LocalTime sunset, City city) {
        this.dayOfWeek = dayOfWeek;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.city = city;
    }

    public Forecast() {

    }

    public Long getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public City getCity() {
        return city;
    }
}
