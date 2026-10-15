package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.entity.Harvest;
import com.agrios.agriosbackend.repository.HarvestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HarvestService {

    private final HarvestRepository repository;

    public HarvestService(HarvestRepository repository) {
        this.repository = repository;
    }

    public Harvest add(Harvest harvest) {
        return repository.save(harvest);
    }

    public List<Harvest> getAll() {
        return repository.findAll();
    }

    public Harvest getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Harvest record not found"));
    }

    public Harvest update(Long id, Harvest updated) {

        Harvest harvest = getById(id);

        harvest.setHarvestDate(updated.getHarvestDate());
        harvest.setQuantity(updated.getQuantity());
        harvest.setUnit(updated.getUnit());
        harvest.setRevenue(updated.getRevenue());
        harvest.setQuality(updated.getQuality());

        return repository.save(harvest);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
