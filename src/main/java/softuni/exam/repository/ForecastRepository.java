package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entities.DayOfWeek;
import softuni.exam.models.entities.Forecast;

import java.util.List;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {

   // List<Forecast> findAllByDayOfWeekOrderByMaxTemperatureDesc(DayOfWeek day);
    List<Forecast> findAllByDayOfWeekOrderByMaxTemperatureDescIdAsc(DayOfWeek day);
}
