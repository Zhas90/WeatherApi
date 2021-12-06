package kz.my.demo.controller;

import kz.my.demo.entity.Weather;
import kz.my.demo.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping
    public ResponseEntity<Weather> addWeather(@Valid @RequestBody Weather weather) {
        logger.info("REST request to add new weather {}", weather);

        Weather result = weatherService.create(weather);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getWeatherList(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                        @RequestParam(required = false) List<String> city,
                                                        @RequestParam(required = false) String sort) {
        logger.debug("REST request to get weather list");

        return ResponseEntity.ok().body(weatherService.getWeatherList(date, city, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weather> getWeatherList(@PathVariable Long id) {
        logger.debug("REST request to get weather by id {}", id);

        Weather weather = weatherService.getWeatherById(id);

        return Optional.ofNullable(weather).map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
