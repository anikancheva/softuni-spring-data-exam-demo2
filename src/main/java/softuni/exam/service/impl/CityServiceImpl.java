package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityDto;
import softuni.exam.models.entities.City;
import softuni.exam.models.entities.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CityServiceImpl implements CityService {

    private static final String CITIES_JSON_PATH="src/main/resources/files/json/cities.json";
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;

    private final Validator validator;

    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository, Gson gson, Validator validator) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.findAll().size()>0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(CITIES_JSON_PATH));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder sb=new StringBuilder();
        CityDto[] citiesDtos = gson.fromJson(readCitiesFileContent(), CityDto[].class);
        for(CityDto dto: citiesDtos){
            Country country=countryRepository.findById(dto.getCountry()).orElse(null);
            City city=new City(dto.getCityName(), dto.getDescription(), dto.getPopulation(), country);
            if(validator.validate(city).isEmpty()){
                try{
                    cityRepository.save(city);
                    sb.append(String.format("Successfully imported city %s - %s", city.getCityName(), city.getPopulation())).append(System.lineSeparator());
                }catch (Exception e){
                    sb.append("Invalid city").append(System.lineSeparator());
                }

            }else {
                sb.append("Invalid city").append(System.lineSeparator());
            }

        }
        return sb.toString();
    }

}
