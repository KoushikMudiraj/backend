package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.entity.Advisory;
import com.agrios.agriosbackend.repository.AdvisoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvisoryService {

    private final AdvisoryRepository repository;

    public AdvisoryService(AdvisoryRepository repository) {
        this.repository = repository;
    }

    public Advisory add(Advisory advisory) {
        return repository.save(advisory);
    }

    public List<Advisory> getAll() {
        return repository.findAll();
    }

    public Advisory getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Advisory not found"));
    }

    public Advisory update(Long id, Advisory updated) {

        Advisory advisory = getById(id);

        advisory.setRecommendation(updated.getRecommendation());
        advisory.setAdvisoryDate(updated.getAdvisoryDate());
        advisory.setOfficerName(updated.getOfficerName());

        return repository.save(advisory);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
