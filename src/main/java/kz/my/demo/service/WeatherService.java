package kz.my.demo.service;

import kz.my.demo.entity.Weather;
import kz.my.demo.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather create(Weather weather) {
        return weatherRepository.addWeather(weather);
    }

    public Weather getWeatherById(Long id) {
        return weatherRepository.getWeatherById(id);
    }

    public List<Weather> getWeatherList(Date date, List<String> city, String sort) {
        List<Weather> weatherList = weatherRepository.getWeatherList();

        List<Predicate<Weather>> predicates = new ArrayList<>();
        if (Objects.nonNull(date)) {
            predicates.add(weather -> toLocalDate(date).isEqual(toLocalDate(weather.getDate())));
        }
        if (Objects.nonNull(city) && !city.isEmpty()) {
            List<String> lowerCaseCities = city.stream().map(String::toLowerCase).collect(Collectors.toList());
            predicates.add(weather -> lowerCaseCities.contains(weather.getCity().toLowerCase()));
        }

        Comparator<Weather> comparator;
        if ("date".equals(sort)) {
            comparator = Comparator
                    .comparing(Weather::getDate)
                    .thenComparing(Weather::getId);
        } else if ("-date".equals(sort)) {
            comparator = Comparator
                    .comparing(Weather::getDate).reversed()
                    .thenComparing(Weather::getId);
        } else {
            comparator = Comparator.comparing(Weather::getId);
        }

        return weatherList.stream()
                .filter(predicates.stream().reduce(x -> true, Predicate::and))
                .sorted(comparator).collect(Collectors.toList());
    }

    private static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
