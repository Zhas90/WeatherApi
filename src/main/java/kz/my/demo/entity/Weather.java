package kz.my.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Double> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Double> temperatures) {
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
