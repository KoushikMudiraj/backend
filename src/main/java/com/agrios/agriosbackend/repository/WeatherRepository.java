package com.agrios.agriosbackend.repository;

import com.agrios.agriosbackend.entity.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository
        extends JpaRepository<WeatherRecord, Long> {
}
