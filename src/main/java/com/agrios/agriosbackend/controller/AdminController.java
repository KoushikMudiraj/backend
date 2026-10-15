package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.entity.User;
import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.entity.Crop;
import com.agrios.agriosbackend.repository.UserRepository;
import com.agrios.agriosbackend.repository.FarmRepository;
import com.agrios.agriosbackend.repository.CropRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final UserRepository userRepository;
    private final FarmRepository farmRepository;
    private final CropRepository cropRepository;

    public AdminController(UserRepository userRepository,
                           FarmRepository farmRepository,
                           CropRepository cropRepository) {

        this.userRepository = userRepository;
        this.farmRepository = farmRepository;
        this.cropRepository = cropRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/farms")
    public List<Farm> getFarms() {
        return farmRepository.findAll();
    }

    @GetMapping("/crops")
    public List<Crop> getCrops() {
        return cropRepository.findAll();
    }
}