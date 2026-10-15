package com.agrios.agriosbackend.repository;

import com.agrios.agriosbackend.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}