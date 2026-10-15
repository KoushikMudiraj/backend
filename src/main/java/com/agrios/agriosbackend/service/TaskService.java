package com.agrios.agriosbackend.service;

import com.agrios.agriosbackend.entity.Task;
import com.agrios.agriosbackend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task add(Task task) {
        return repository.save(task);
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task update(Long id, Task updated) {
        Task task = getById(id);

        task.setTitle(updated.getTitle());
        task.setDescription(updated.getDescription());
        task.setDueDate(updated.getDueDate());
        task.setPriority(updated.getPriority());
        task.setStatus(updated.getStatus());

        return repository.save(task);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
