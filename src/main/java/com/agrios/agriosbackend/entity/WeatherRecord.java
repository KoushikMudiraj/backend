package com.agrios.agriosbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "weather_records")
public class WeatherRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "record_date")
    private String date;

    private double temperature;
    private double humidity;
    private double rainfall;

    @Column(name = "weather_condition")
    private String condition;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    public WeatherRecord() {
    }

    public WeatherRecord(String date, double temperature,
                         double humidity, double rainfall,
                         String condition, Farm farm) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.rainfall = rainfall;
        this.condition = condition;
        this.farm = farm;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getRainfall() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}