package com.softwaremind.todotracker.boundary.controller;

import com.softwaremind.todotracker.boundary.dto.AddTaskRequestDto;
import com.softwaremind.todotracker.boundary.dto.AddTaskResponseDto;
import com.softwaremind.todotracker.boundary.dto.TaskResponseDto;
import com.softwaremind.todotracker.control.service.TaskServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/tasks")
public class TaskController {
    private TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<AddTaskResponseDto> addTask(@RequestBody @Valid AddTaskRequestDto taskDto){
        AddTaskResponseDto addedTask = taskService.addTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTask);
    }

@GetMapping("/{id}")
    public ResponseEntity <TaskResponseDto> getTask (@PathVariable Long id){
        TaskResponseDto retrievedTask = taskService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(retrievedTask);
}


}
