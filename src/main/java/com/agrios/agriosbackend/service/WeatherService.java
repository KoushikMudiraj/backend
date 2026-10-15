package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.entity.WeatherRecord;
import com.agrios.agriosbackend.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final WeatherRepository repository;

    public WeatherService(WeatherRepository repository) {
        this.repository = repository;
    }

    public WeatherRecord add(WeatherRecord weather) {
        return repository.save(weather);
    }

    public List<WeatherRecord> getAll() {
        return repository.findAll();
    }

    public WeatherRecord getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Weather record not found"));
    }

    public WeatherRecord update(Long id, WeatherRecord updated) {

        WeatherRecord weather = getById(id);

        weather.setDate(updated.getDate());
        weather.setTemperature(updated.getTemperature());
        weather.setHumidity(updated.getHumidity());
        weather.setRainfall(updated.getRainfall());
        weather.setCondition(updated.getCondition());

        return repository.save(weather);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}