package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
