package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entities.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByPopulationLessThan(int population);
}
