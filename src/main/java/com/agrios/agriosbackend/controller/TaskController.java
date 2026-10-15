package com.agrios.agriosbackend.controller;

import com.agrios.agriosbackend.dto.TaskRequest;
import com.agrios.agriosbackend.entity.Farm;
import com.agrios.agriosbackend.entity.Task;
import com.agrios.agriosbackend.repository.FarmRepository;
import com.agrios.agriosbackend.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    private final TaskService service;
    private final FarmRepository farmRepository;

    public TaskController(TaskService service,
                          FarmRepository farmRepository) {
        this.service = service;
        this.farmRepository = farmRepository;
    }

    @PostMapping
    public Task add(@RequestBody TaskRequest request) {

        Farm farm = farmRepository.findById(request.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                request.getDueDate(),
                request.getPriority(),
                request.getStatus(),
                farm
        );

        return service.add(task);
    }

    @GetMapping
    public List<Task> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id,
                       @RequestBody TaskRequest request) {

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());

        return service.update(id, task);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Task deleted successfully";
    }
}
