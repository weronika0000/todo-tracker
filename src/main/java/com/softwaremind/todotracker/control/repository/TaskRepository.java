package com.softwaremind.todotracker.control.repository;

import com.softwaremind.todotracker.entity.Task;
import com.softwaremind.todotracker.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {


    List<Task> findByStatusOrderByDeadlineAsc(TaskStatus status);
    List<Task> findByStatusOrderByDeadlineDesc(TaskStatus status);
    List<Task> findByStatusOrderByImportanceAsc(TaskStatus status);
    List<Task> findByStatusOrderByImportanceDesc(TaskStatus status);



}


