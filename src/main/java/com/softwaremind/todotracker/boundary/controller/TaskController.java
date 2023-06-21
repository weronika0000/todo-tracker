package com.softwaremind.todotracker.boundary.controller;

import com.softwaremind.todotracker.boundary.dto.*;
import com.softwaremind.todotracker.control.service.TaskServiceImpl;
import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskServiceImpl taskService;
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<AddTaskResponseDto> addTask(@RequestBody @Valid AddAndModifyTaskRequestDto taskDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addTask(taskDto));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Long taskId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getById(taskId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> modifyTask(@PathVariable Long taskId, @RequestBody @Valid AddAndModifyTaskRequestDto taskDto) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.modifyTask(taskId, taskDto));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        String body = "The task was deleted";
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<TaskResponseDto>> getFilteredTasks(
            @RequestParam(value = "status", required = false) TaskStatus status,
            @RequestParam(value = "importance", required = false) TaskImportance importance,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") String sortDirection) {

        return ResponseEntity.status(HttpStatus.OK).body(taskService.getFilteredTasks(status, importance, sortBy, sortDirection));

    }
}