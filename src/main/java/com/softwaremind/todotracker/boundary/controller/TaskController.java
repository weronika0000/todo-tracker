package com.softwaremind.todotracker.boundary.controller;

import com.softwaremind.todotracker.boundary.dto.*;
import com.softwaremind.todotracker.control.service.TaskServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

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


    //messages


    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .collect(toMap(
                        err -> ((FieldError) err).getField(),
                        err -> Optional.ofNullable(err.getDefaultMessage()).orElse("field error"),
                        (msg1, msg2) -> Objects.equals(msg1, msg2) ? msg1 : msg1 + ", " + msg2
                ));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .collect(toMap(
                        err -> err.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));
    }


}
