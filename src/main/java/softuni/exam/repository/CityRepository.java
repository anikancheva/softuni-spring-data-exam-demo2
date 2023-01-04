package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
