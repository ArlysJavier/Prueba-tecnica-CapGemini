package com.capgemini.prueba_tecnica_admintask.service;

import com.capgemini.prueba_tecnica_admintask.model.TaskEntity;
import com.capgemini.prueba_tecnica_admintask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity save(TaskEntity task) {
        return taskRepository.save(task);
    }

    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

    public Optional<TaskEntity> findById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}