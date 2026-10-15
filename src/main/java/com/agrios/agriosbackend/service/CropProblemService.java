package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.entity.CropProblem;
import com.agrios.agriosbackend.repository.CropProblemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropProblemService {

    private final CropProblemRepository repository;

    public CropProblemService(CropProblemRepository repository) {
        this.repository = repository;
    }

    public CropProblem add(CropProblem problem) {
        return repository.save(problem);
    }

    public List<CropProblem> getAll() {
        return repository.findAll();
    }

    public CropProblem getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Crop problem not found"));
    }

    public CropProblem update(Long id, CropProblem updated) {

        CropProblem problem = getById(id);

        problem.setProblemType(updated.getProblemType());
        problem.setDescription(updated.getDescription());
        problem.setReportedDate(updated.getReportedDate());
        problem.setStatus(updated.getStatus());

        return repository.save(problem);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
