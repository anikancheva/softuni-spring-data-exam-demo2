package softuni.exam.models.dto;

public class CityDto {

    private String cityName;
    private String description;
    private int population;
    private long country;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }
}
