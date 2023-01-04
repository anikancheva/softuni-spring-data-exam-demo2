package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.models.entities.DayOfWeek;
import softuni.exam.models.entities.Forecast;

import java.util.List;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    @Query("SELECT f from Forecast f where f.dayOfWeek=?1 and f.city.population<150000 order by f.maxTemperature desc, f.id asc")
    List<Forecast> findAllByDayOfWeekAndPopulation(DayOfWeek day);
}
