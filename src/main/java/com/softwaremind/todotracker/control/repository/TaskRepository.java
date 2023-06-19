package com.softwaremind.todotracker.control.repository;

import com.softwaremind.todotracker.entity.Task;
import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {
    List<Task> findAllByStatus(TaskStatus status, Sort sort);
    List<Task> findAllByImportance(TaskImportance importance, Sort sort);
    List<Task> findAllByStatusAndImportance(TaskStatus status, TaskImportance importance, Sort sort);




}


