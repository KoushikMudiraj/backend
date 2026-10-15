package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.WeatherRequest;
import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.entity.WeatherRecord;
import com.agrios.agriosbackend.repository.FarmRepository;
import com.agrios.agriosbackend.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherController {

    private final WeatherService service;
    private final FarmRepository farmRepository;

    public WeatherController(WeatherService service,
                             FarmRepository farmRepository) {
        this.service = service;
        this.farmRepository = farmRepository;
    }

    @PostMapping
    public WeatherRecord add(@RequestBody WeatherRequest request) {

        Farm farm = farmRepository.findById(request.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        WeatherRecord weather = new WeatherRecord(
                request.getDate(),
                request.getTemperature(),
                request.getHumidity(),
                request.getRainfall(),
                request.getCondition(),
                farm
        );

        return service.add(weather);
    }

    @GetMapping
    public List<WeatherRecord> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WeatherRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public WeatherRecord update(
            @PathVariable Long id,
            @RequestBody WeatherRequest request) {

        WeatherRecord weather = new WeatherRecord();

        weather.setDate(request.getDate());
        weather.setTemperature(request.getTemperature());
        weather.setHumidity(request.getHumidity());
        weather.setRainfall(request.getRainfall());
        weather.setCondition(request.getCondition());

        return service.update(id, weather);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Weather record deleted successfully";
    }
}
