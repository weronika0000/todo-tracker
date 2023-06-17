package com.softwaremind.todotracker.control.service;
import com.softwaremind.todotracker.boundary.dto.ModifyTaskRequestDto;
import com.softwaremind.todotracker.boundary.dto.ModifyTaskResponseDto;
import com.softwaremind.todotracker.boundary.dto.AddTaskRequestDto;
import com.softwaremind.todotracker.boundary.dto.AddTaskResponseDto;
import com.softwaremind.todotracker.boundary.mapper.TaskMapper;
import com.softwaremind.todotracker.control.repository.TaskRepository;
import com.softwaremind.todotracker.control.service.TaskServiceImpl;
import com.softwaremind.todotracker.entity.Task;
import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
public class TaskServiceImplTests {

    @Mock
    private TaskRepository mockTaskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskServiceImpl(mockTaskRepository);
    }

    @Test
    public void testAddTask_WithValidRequest_ReturnsAddTaskResponseDto() {
        // Prepare
        AddTaskRequestDto requestDto = new AddTaskRequestDto(
                "Sample Title",
                "Sample Details",
                TaskStatus.doing,
                TaskImportance.high,
                LocalDate.now().plusDays(7)
        );

        Task task = TaskMapper.mapAddTaskRequestDtotoTask(requestDto);
        task.setCreatedAt(Instant.now());
        Task savedTask = new Task();
        savedTask.setTaskId(1L);
        savedTask.setTitle("Sample Title");
        savedTask.setDetails("Sample Details");
        savedTask.setStatus(TaskStatus.doing);
        savedTask.setImportance(TaskImportance.high);
        savedTask.setDeadline(LocalDate.now().plusDays(7));
        savedTask.setCreatedAt(Instant.now());

        AddTaskResponseDto expectedResponseDto = TaskMapper.mapTasktoAddTaskResponseDto(savedTask);

        when(mockTaskRepository.save(any(Task.class))).thenReturn(savedTask);

        // Execute
        AddTaskResponseDto responseDto = taskService.addTask(requestDto);

        // Verify
        assertEquals(expectedResponseDto, responseDto);
    }

/*    @Test
    void testAddTask_WithInvalidRequest_ShouldThrowException() {
        // Arrange
        String title = null;
        String details = "Sample Details";
        TaskStatus status = TaskStatus.doing;
        TaskImportance importance = TaskImportance.high;
        LocalDate deadline = LocalDate.of(2023, 7, 25);

        // Act
        //
        // & Assert
       ;
    }*/



    @Test
    void testModifyTask_WithValidRequest_ShouldReturnModifiedTask() {
        // Arrange
        Long taskId = 1L; // Existing task ID
        ModifyTaskRequestDto taskDto = new ModifyTaskRequestDto(
                "Updated Title",
                "Updated Details",
                TaskStatus.done,
                TaskImportance.low,
                LocalDate.parse("2023-06-30")
        );

        Task existingTask = new Task();
        existingTask.setTaskId(taskId);
        existingTask.setTitle("Original Title");
        existingTask.setDetails("Original Details");
        existingTask.setStatus(TaskStatus.doing);
        existingTask.setImportance(TaskImportance.high);
        existingTask.setDeadline(LocalDate.parse("2023-06-24"));

        // Mock dependencies
        when(mockTaskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(mockTaskRepository.save(existingTask)).thenReturn(existingTask);

        // Act
        ModifyTaskResponseDto response = taskService.modifyTask(taskId, taskDto);

        // Assert
        assertNotNull(response);
        assertEquals(taskId, response.taskId());
        assertEquals(taskDto.title(), response.title());
        assertEquals(taskDto.details(), response.details());
        assertEquals(taskDto.status(), response.status());
        assertEquals(taskDto.importance(), response.importance());
        assertEquals(taskDto.deadline(), response.deadline());
    }

    @Test
    void testModifyTask_WithNonExistingTask_ShouldThrowException() {
        // Arrange
        Long taskId = 1L; // Non-existing task ID
        ModifyTaskRequestDto taskDto = new ModifyTaskRequestDto(
                "Updated Title",
                "Updated Details",
                TaskStatus.done,
                TaskImportance.low,
                LocalDate.parse("2023-06-30")
        );

        // Mock dependencies
        when(mockTaskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> taskService.modifyTask(taskId, taskDto));
    }





}
