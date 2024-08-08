package com.capgemini.prueba_tecnica_admintask.controller;

import com.capgemini.prueba_tecnica_admintask.model.TaskEntity;
import com.capgemini.prueba_tecnica_admintask.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskEntity createTask(@Valid @RequestBody TaskEntity task) {
        task.setCreatedAt(new Date());
        task.setStatus(TaskEntity.Status.PENDING);
        return taskService.save(task);
    }

    @GetMapping
    public List<TaskEntity> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable(value = "id") Long taskId) {
        Optional<TaskEntity> task = taskService.findById(taskId);
        return task.map(taskEntity -> ResponseEntity.ok().body(taskEntity)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable(value = "id") Long taskId,
                                           @Valid @RequestBody TaskEntity taskDetails) {
        Optional<TaskEntity> task = taskService.findById(taskId);
        if (task.isPresent()) {
            TaskEntity taskToUpdate = task.get();
            taskToUpdate.setTitle(taskDetails.getTitle());
            taskToUpdate.setDescription(taskDetails.getDescription());
            taskToUpdate.setStatus(taskDetails.getStatus());
            final TaskEntity updatedTask = taskService.save(taskToUpdate);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(value = "id") Long taskId) {
        Optional<TaskEntity> task = taskService.findById(taskId);
        if (task.isPresent()) {
            taskService.deleteById(taskId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}