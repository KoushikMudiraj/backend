package com.agrios.agriosbackend.repository;

import com.agrios.agriosbackend.entity.Advisory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisoryRepository
        extends JpaRepository<Advisory, Long> {
}
