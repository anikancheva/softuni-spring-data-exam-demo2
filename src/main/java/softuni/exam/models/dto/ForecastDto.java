package softuni.exam.models.dto;

import softuni.exam.util.LocalTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastDto {
    @XmlElement(name = "day_of_week")
    private String dayOfWeek;
    @XmlElement(name = "max_temperature")
    private double maxTemperature;
    @XmlElement(name = "min_temperature")
    private double minTemperature;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)
    private LocalTime sunrise;
    @XmlJavaTypeAdapter(LocalTimeAdapter.class)

    private LocalTime sunset;
    private long city;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public long getCity() {
        return city;
    }

    public void setCity(long city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return dayOfWeek + " / " + maxTemperature + " / " + minTemperature + " / " + sunrise + " / " + sunset + " / " + city;
    }
}
