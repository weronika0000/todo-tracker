package com.softwaremind.todotracker.boundary.controller;

import com.softwaremind.todotracker.boundary.dto.AddAndModifyTaskRequestDto;
import com.softwaremind.todotracker.boundary.dto.AddTaskResponseDto;
import com.softwaremind.todotracker.boundary.dto.TaskResponseDto;
import com.softwaremind.todotracker.control.service.TaskServiceImpl;
import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskControllerTests {

    @Mock
    private TaskServiceImpl taskService;

    @InjectMocks
    private TaskController taskController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addTask_shouldReturnCreatedStatusAndAddTaskResponseDto() {
        // Arrange
        LocalDate deadline = LocalDate.now().plusDays(5);
        Instant createdAt = Instant.now();
        AddAndModifyTaskRequestDto taskDto = new AddAndModifyTaskRequestDto("Sample Title", "Sample Details",
                TaskStatus.TODO, TaskImportance.LOW, deadline);
        AddTaskResponseDto expectedResponse = new AddTaskResponseDto(1L, "Sample Title", "Sample Details",
                TaskStatus.TODO, TaskImportance.LOW, deadline,createdAt );
        when(taskService.addTask(taskDto)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<AddTaskResponseDto> response = taskController.addTask(taskDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(taskService, times(1)).addTask(taskDto);
    }


    @Test
    void modifyTask_shouldReturnOkStatusAndTaskResponseDto() {
        // Arrange
        Long taskId = 1L;
        LocalDate deadline = LocalDate.now().plusDays(5);
        Instant createdAt = Instant.now();
        Instant modifiedAt = Instant.now();// Set the deadline to 5 days from now
        AddAndModifyTaskRequestDto taskDto = new AddAndModifyTaskRequestDto("Sample Title", "Sample Details",
                TaskStatus.TODO, TaskImportance.LOW, deadline);
        TaskResponseDto expectedResponse = new TaskResponseDto(taskId, "Sample Title2", "Sample Details2",
                TaskStatus.TODO, TaskImportance.LOW, deadline, createdAt, modifiedAt);
        when(taskService.modifyTask(taskId, taskDto)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<TaskResponseDto> response = taskController.modifyTask(taskId, taskDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(taskService, times(1)).modifyTask(taskId, taskDto);
    }

}
