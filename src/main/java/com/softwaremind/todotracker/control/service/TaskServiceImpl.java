package com.softwaremind.todotracker.control.service;

import com.softwaremind.todotracker.boundary.dto.*;
import com.softwaremind.todotracker.control.exceptions.TaskNotFoundException;
import com.softwaremind.todotracker.control.repository.TaskRepository;
import com.softwaremind.todotracker.entity.Task;
import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static com.softwaremind.todotracker.boundary.mapper.TaskMapper.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public AddTaskResponseDto addTask(AddAndModifyTaskRequestDto taskDto) {
        Task receivedTask = mapAddAndModifyTaskRequestDtotoTask(taskDto);
        receivedTask.setCreatedAt(Instant.now());
        Task responseTask = taskRepository.save(receivedTask);
        return mapTasktoAddTaskResponseDto(responseTask);
    }

    @Override
    public TaskResponseDto getById(Long taskId) {
        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException("The task does not exist"));

        return mapTasktoTaskResponseDto(task);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found");

        }
        return mapTaskListToTaskResponseDtoList(tasks);
    }


    @Override
    public TaskResponseDto modifyTask(Long taskId, AddAndModifyTaskRequestDto taskDto) {

        Task taskFromDatabase = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException("The task does not exist"));

        taskFromDatabase.setTitle(taskDto.title());
        taskFromDatabase.setDetails(taskDto.details());
        taskFromDatabase.setStatus(taskDto.status());
        taskFromDatabase.setImportance(taskDto.importance());
        taskFromDatabase.setDeadline(taskDto.deadline());
        taskFromDatabase.setModifiedAt(Instant.now());
        Task responseTask = taskRepository.save(taskFromDatabase);

        return mapTasktoTaskResponseDto(responseTask);
    }

    @Override
    public void deleteTask(Long taskId) {

        Task taskFromDatabase = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException("The task does not exist"));

        taskRepository.deleteById(taskId);


    }

    @Override
    public List<TaskResponseDto> getFilteredTasks(TaskStatus status, TaskImportance importance, String sortBy, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort;

        if ("importance".equalsIgnoreCase(sortBy)) {
            sort = Sort.by(direction, "importance");
        } else {
            sort = Sort.by(direction, "deadline");
        }

        List<Task> filteredTasks;
        if (status != null && importance != null) {
            filteredTasks = taskRepository.findAllByStatusAndImportance(status, importance, sort);
        } else if (status != null) {
            filteredTasks = taskRepository.findAllByStatus(status, sort);
        } else if (importance != null) {
            sort = Sort.by(direction, "deadline");
            filteredTasks = taskRepository.findAllByImportance(importance, sort);
        } else {
            filteredTasks = taskRepository.findAll(sort);
        }

        if (filteredTasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found");
        }

        return mapTaskListToTaskResponseDtoList(filteredTasks);
    }

}



















