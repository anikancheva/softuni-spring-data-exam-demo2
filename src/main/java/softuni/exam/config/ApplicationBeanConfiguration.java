package softuni.exam.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.Validator;

public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
