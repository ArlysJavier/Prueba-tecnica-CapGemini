package com.capgemini.prueba_tecnica_admintask.repository;

import com.capgemini.prueba_tecnica_admintask.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
