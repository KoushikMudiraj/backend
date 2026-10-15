package com.agrios.agriosbackend.repository;

import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmRepository extends JpaRepository<Farm, Long> {

    List<Farm> findByOwner(User owner);
}