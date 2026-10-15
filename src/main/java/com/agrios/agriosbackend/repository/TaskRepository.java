package com.agrios.agriosbackend.repository;

import com.agrios.agriosbackend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
