package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastDto;
import softuni.exam.models.dto.ForecastsRootDto;
import softuni.exam.models.entities.City;
import softuni.exam.models.entities.DayOfWeek;
import softuni.exam.models.entities.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.XmlParser;

import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static final String FORECAST_XML_PATH = "src/main/resources/files/xml/forecasts.xml";
    private final XmlParser xmlParser;
    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;
    private final Validator validator;

    public ForecastServiceImpl(XmlParser xmlParser, ForecastRepository forecastRepository, CityRepository cityRepository, Validator validator) {
        this.xmlParser = xmlParser;
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.findAll().size() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FORECAST_XML_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        List<ForecastDto> forecastDtos = xmlParser.unmarshal(FORECAST_XML_PATH, ForecastsRootDto.class).getForecasts();
        for (ForecastDto dto : forecastDtos) {
            City city = cityRepository.findById(dto.getCity()).orElse(null);
            try {
                DayOfWeek day = DayOfWeek.valueOf(dto.getDayOfWeek());
                Forecast forecast = new Forecast(day, dto.getMaxTemperature(),
                        dto.getMinTemperature(), dto.getSunrise(), dto.getSunset(), city);
                if (validator.validate(forecast).isEmpty()) {
                    forecastRepository.save(forecast);
                    sb.append(String.format("Successfully imported forecast %s - %s", forecast.getDayOfWeek(), forecast.getMaxTemperature())).append(System.lineSeparator());
                } else {
                    sb.append("Invalid forecast").append(System.lineSeparator());
                }
            } catch (Exception e) {
                sb.append("Invalid forecast").append(System.lineSeparator());
            }

        }
        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        List<Forecast> sundayForecasts = forecastRepository.findAllByDayOfWeekOrderByMaxTemperatureDescIdAsc(DayOfWeek.valueOf("SUNDAY"));
        StringBuilder result = new StringBuilder();
        for (Forecast f : sundayForecasts) {
            City city = f.getCity();
            if (city.getPopulation() < 150000) {
                result.append(String.format("City: %s\n   -min temperature: %.2f\n   --max temperature: %.2f\n   ---sunrise: %s\n   ----sunset: %s\n", city.getCityName(), f.getMinTemperature(), f.getMaxTemperature(), f.getSunrise(), f.getSunset()));
            }
        }

        return result.toString();
    }
}
