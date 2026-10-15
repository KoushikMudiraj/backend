package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.AdvisoryRequest;
import com.agrios.agriosbackend.entity.Advisory;
import com.agrios.agriosbackend.entity.CropProblem;
import com.agrios.agriosbackend.repository.CropProblemRepository;
import com.agrios.agriosbackend.service.AdvisoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisories")
@CrossOrigin(origins = "http://localhost:5173")
public class AdvisoryController {

    private final AdvisoryService service;
    private final CropProblemRepository problemRepository;

    public AdvisoryController(
            AdvisoryService service,
            CropProblemRepository problemRepository) {
        this.service = service;
        this.problemRepository = problemRepository;
    }

    @PostMapping
    public Advisory add(@RequestBody AdvisoryRequest request) {

        CropProblem problem = problemRepository.findById(request.getProblemId())
                .orElseThrow(() ->
                        new RuntimeException("Crop problem not found"));

        Advisory advisory = new Advisory(
                request.getRecommendation(),
                request.getAdvisoryDate(),
                request.getOfficerName(),
                problem
        );

        return service.add(advisory);
    }

    @GetMapping
    public List<Advisory> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Advisory getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Advisory update(
            @PathVariable Long id,
            @RequestBody AdvisoryRequest request) {

        Advisory advisory = new Advisory();

        advisory.setRecommendation(request.getRecommendation());
        advisory.setAdvisoryDate(request.getAdvisoryDate());
        advisory.setOfficerName(request.getOfficerName());

        return service.update(id, advisory);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Advisory deleted successfully";
    }
}
