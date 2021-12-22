package kz.my.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.my.demo.entity.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WeatherControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAddWeatherSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/weather";
        URI uri = new URI(baseUrl);

        List<Double> temperatures = Arrays.asList(17.3, 16.8, 16.4, 16.0, 15.6, 15.3, 15.0, 14.9, 15.8, 18.0, 20.2, 22.3, 23.8, 24.9, 25.5, 25.7, 24.9, 23.0, 21.7, 20.8, 29.9, 29.2, 28.6, 28.1);
        Weather weather = new Weather(new Date(), 51.1801, 71.446, "Astana", "Astana", temperatures);

        HttpEntity<Weather> request = new HttpEntity<>(weather);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assertions.assertEquals(201, result.getStatusCodeValue());

        Weather createdWeather = stringToWeather(result.getBody());
        Assertions.assertNotNull(createdWeather);
        Assertions.assertNotNull(createdWeather.getId());
    }

    @Test
    public void testAddWeatherBadRequest() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/weather";
        URI uri = new URI(baseUrl);

        Weather weather = new Weather();
        HttpEntity<Weather> request = new HttpEntity<>(weather);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify bad request
        Assertions.assertEquals(400, result.getStatusCodeValue());
    }

    private static Weather stringToWeather(String string) {
        try {
            return new ObjectMapper().readValue(string, Weather.class);
        } catch (Exception e) {
            return null;
        }
    }

}
