package kz.my.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.my.demo.controller.WeatherController;
import kz.my.demo.entity.Weather;
import kz.my.demo.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
public class WeatherTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void addWeatherShouldReturnBadRequest() throws Exception {
        Weather weather = new Weather();

        this.mockMvc.perform(post("/weather")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(weather)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addWeatherShouldReturnCreated() throws Exception {
        Weather weather = new Weather();
        weather.setCity("Astana");
        weather.setState("Astana");
        weather.setDate(new Date());
        weather.setLat(51.1801);
        weather.setLon(71.446);
        weather.setTemperatures(Arrays.asList(17.3, 16.8, 16.4, 16.0, 15.6, 15.3, 15.0, 14.9, 15.8, 18.0, 20.2, 22.3, 23.8, 24.9, 25.5, 25.7, 24.9, 23.0, 21.7, 20.8, 29.9, 29.2, 28.6, 28.1));

        this.mockMvc.perform(post("/weather")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(weather)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
