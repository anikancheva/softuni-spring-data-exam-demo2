package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import softuni.exam.models.entities.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CountryServiceImpl implements CountryService {

    private static final String COUNTRIES_JSON_PATH="src/main/resources/files/json/countries.json";
    private final CountryRepository countryRepository;
    private final Gson gson;

    private final Validator validator;

    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, Validator validator) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validator = validator;
    }


    @Override
    public boolean areImported() {
        return this.countryRepository.findAll().size()>0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(COUNTRIES_JSON_PATH));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb=new StringBuilder();
        Country[] countries = gson.fromJson(readCountriesFromFile(), Country[].class);
        for (Country country:countries){
            if(validator.validate(country).isEmpty()){
                try{
                    countryRepository.save(country);
                    sb.append(String.format("Successfully imported country %s - %s", country.getCountryName(), country.getCurrency())).append(System.lineSeparator());
                }catch (Exception e){
                    sb.append("Invalid country").append(System.lineSeparator());
                }

            }else {
                sb.append("Invalid country").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
