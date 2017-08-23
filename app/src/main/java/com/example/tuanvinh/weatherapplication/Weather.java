package com.example.tuanvinh.weatherapplication;

/**
 * Created by TuanVinh on 8/24/2017.
 */

public class Weather {
    Integer id;
    String name;
    String main;
    String description;
    Double temp;
    Double humidity;
    Double speed;

    public Weather() {
    }

    public Weather(Integer id, String name, String main) {
        this.id = id;
        this.name = name;
        this.main = main;
        this.description = description;
        temp = temp;
        this.humidity = humidity;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", temp=" + temp +
                ", humidity=" + humidity +
                ", speed=" + speed +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}

