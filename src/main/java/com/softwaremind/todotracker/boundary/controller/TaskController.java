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
    public ResponseEntity<AddTaskResponseDto> addTask(@RequestBody @Valid AddTaskRequestDto taskDto) {
        AddTaskResponseDto addedTask = taskService.addTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Long id) {
        TaskResponseDto retrievedTask = taskService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(retrievedTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModifyTaskResponseDto> modifyTask(@PathVariable Long id, @RequestBody @Valid ModifyTaskRequestDto taskDto) {
        ModifyTaskResponseDto modifiedTask = taskService.modifyTask(id, taskDto);
        return ResponseEntity.status(HttpStatus.OK).body(modifiedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        String body = "The task was deleted";
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    //
    @GetMapping("/list")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> allTasks = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(allTasks);
    }

    //                              filters

    @GetMapping("/filtered")
    public ResponseEntity<List<TaskResponseDto>> getFilteredTasks(
            @RequestParam(value = "status", required = false) TaskStatus status,
            @RequestParam(value = "importance", required = false) TaskImportance importance,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") String sortDirection) {

        List<TaskResponseDto> tasks = taskService.getFilteredTasks(status, importance, sortBy, sortDirection);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);

    }
}