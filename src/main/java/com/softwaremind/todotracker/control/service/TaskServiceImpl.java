package com.softwaremind.todotracker.control.service;

import com.softwaremind.todotracker.boundary.dto.AddTaskRequestDto;
import com.softwaremind.todotracker.boundary.dto.AddTaskResponseDto;
import com.softwaremind.todotracker.boundary.dto.TaskResponseDto;
import com.softwaremind.todotracker.control.repository.TaskRepository;
import com.softwaremind.todotracker.entity.Task;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.softwaremind.todotracker.boundary.mapper.TaskMapper.mapTaskRequestDtotoTask;
import static com.softwaremind.todotracker.boundary.mapper.TaskMapper.mapTasktoTaskResponseDto;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public AddTaskResponseDto addTask(AddTaskRequestDto taskDto) {
        Task receivedTask = mapTaskRequestDtotoTask(taskDto);
        receivedTask.setCreatedAt(Instant.now());
        Task responseTask = taskRepository.save(receivedTask);
        return mapTasktoTaskResponseDto(responseTask);
    }

    @Override
    public TaskResponseDto findById(Long taskId) {
        return null;
    }

    @Override
    public TaskResponseDto updateTask(Long taskId, AddTaskRequestDto taskDto) {
        return null;
    }

    @Override
    public void deleteTask(Long taskId) {

    }
}
