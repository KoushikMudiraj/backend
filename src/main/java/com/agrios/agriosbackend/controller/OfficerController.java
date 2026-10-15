package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.entity.CropProblem;
import com.agrios.agriosbackend.entity.Advisory;
import com.agrios.agriosbackend.repository.CropProblemRepository;
import com.agrios.agriosbackend.repository.AdvisoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/officer")
@CrossOrigin(origins = "http://localhost:5173")
public class OfficerController {

    private final CropProblemRepository cropProblemRepository;
    private final AdvisoryRepository advisoryRepository;

    public OfficerController(CropProblemRepository cropProblemRepository,
                             AdvisoryRepository advisoryRepository) {

        this.cropProblemRepository = cropProblemRepository;
        this.advisoryRepository = advisoryRepository;
    }

    @GetMapping("/problems")
    public List<CropProblem> getProblems() {
        return cropProblemRepository.findAll();
    }

    @GetMapping("/advisories")
    public List<Advisory> getAdvisories() {
        return advisoryRepository.findAll();
    }

    @PostMapping("/advisories")
    public Advisory addAdvisory(@RequestBody Advisory advisory) {
        return advisoryRepository.save(advisory);
    }
}