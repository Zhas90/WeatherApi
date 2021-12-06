package kz.my.demo.repository;

import kz.my.demo.entity.Weather;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WeatherRepository {

    private static final Map<Long, Weather> weatherMap = new ConcurrentHashMap<>();

    public Weather addWeather(Weather weather) {
        long id = weatherMap.size() + 1;
        weather.setId(id);
        weatherMap.put(id, weather);

        return weather;
    }

    public List<Weather> getWeatherList() {
        return new ArrayList<>(weatherMap.values());
    }

    public Weather getWeatherById(Long id) {
        return weatherMap.get(id);
    }

}
