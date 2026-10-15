package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.CropProblemRequest;
import com.agrios.agriosbackend.entity.Crop;
import com.agrios.agriosbackend.entity.CropProblem;
import com.agrios.agriosbackend.repository.CropRepository;
import com.agrios.agriosbackend.service.CropProblemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crop-problems")
@CrossOrigin(origins = "http://localhost:5173")
public class CropProblemController {

    private final CropProblemService service;
    private final CropRepository cropRepository;

    public CropProblemController(CropProblemService service,
                                 CropRepository cropRepository) {
        this.service = service;
        this.cropRepository = cropRepository;
    }

    @PostMapping
    public CropProblem add(@RequestBody CropProblemRequest request) {

        Crop crop = cropRepository.findById(request.getCropId())
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        CropProblem problem = new CropProblem(
                request.getProblemType(),
                request.getDescription(),
                request.getReportedDate(),
                request.getStatus(),
                crop
        );

        return service.add(problem);
    }

    @GetMapping
    public List<CropProblem> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CropProblem getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CropProblem update(
            @PathVariable Long id,
            @RequestBody CropProblemRequest request) {

        CropProblem problem = new CropProblem();

        problem.setProblemType(request.getProblemType());
        problem.setDescription(request.getDescription());
        problem.setReportedDate(request.getReportedDate());
        problem.setStatus(request.getStatus());

        return service.update(id, problem);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Crop problem deleted successfully";
    }
}