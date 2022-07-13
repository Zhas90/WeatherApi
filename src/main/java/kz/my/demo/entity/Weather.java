package kz.my.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Schema(name="Weather", description="Weather characteristic")
public class Weather {

    private Long id;

    @NotNull(message = "date must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull(message = "lat must not be null")
    private Double lat;

    @NotNull(message = "lon must not be null")
    private Double lon;

    @NotNull(message = "state must not be null")
    @NotBlank(message = "state must not be blank")
    private String state;

    @NotNull(message = "city must not be null")
    @NotBlank(message = "city must not be blank")
    private String city;

    @NotNull(message = "temperatures must not be null")
    @NotEmpty(message = "temperatures must not be empty")
    private List<Double> temperatures;

    public Weather() {
    }

    public Weather(Date date, Double lat, Double lon, String state, String city, List<Double> temperatures) {
        super();
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.state = state;
        this.city = city;
        this.temperatures = temperatures;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + getId() +
                ", date='" + getDate() + "'" +
                ", lat='" + getLat() + "'" +
                ", lon='" + getLon() + "'" +
                ", state='" + getState() + "'" +
                ", city='" + getCity() + "'" +
                ", temperatures='" + getTemperatures() + "'" +
                "}";
    }

}
