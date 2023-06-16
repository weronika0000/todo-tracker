package com.softwaremind.todotracker.control.service;

import com.softwaremind.todotracker.boundary.dto.*;
import com.softwaremind.todotracker.control.repository.TaskRepository;
import com.softwaremind.todotracker.entity.Task;
import com.softwaremind.todotracker.entity.TaskStatus;
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
    public AddTaskResponseDto addTask(AddTaskRequestDto taskDto) {
        Task receivedTask = mapAddTaskRequestDtotoTask(taskDto);
        receivedTask.setCreatedAt(Instant.now());
        Task responseTask = taskRepository.save(receivedTask);
        return mapTasktoAddTaskResponseDto(responseTask);
    }

    @Override
    public TaskResponseDto getById(Long taskId) {
        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new RuntimeException("The task does not exist"));

        return mapTasktoTaskResponseDto(task);
    }

    @Override
    public List<TaskResponseDto> getAllTasks(){
    List<Task> tasks = taskRepository.findAll();

    if (tasks.isEmpty()) {
        throw new RuntimeException("No tasks found");

    }
        return mapTaskListToTaskResponseDtoList(tasks);
    }




    @Override
    public ModifyTaskResponseDto modifyTask(Long taskId, ModifyTaskRequestDto taskDto) {

        Task taskFromDatabase = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new RuntimeException("The task does not exist"));

        taskFromDatabase.setTitle(taskDto.title());
        taskFromDatabase.setDetails(taskDto.details());
        taskFromDatabase.setStatus(taskDto.status());
        taskFromDatabase.setImportance(taskDto.importance());
        taskFromDatabase.setDeadline(taskDto.deadline());
        taskFromDatabase.setModifiedAt(Instant.now());
        Task responseTask = taskRepository.save(taskFromDatabase);

        return mapTasktoModifyTaskResponseDto(responseTask);
    }

    @Override
    public void deleteTask(Long taskId) {

        Task taskFromDatabase = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new RuntimeException("The task does not exist"));

        taskRepository.deleteById(taskId);


        }

    @Override
    public List<TaskResponseDto> getTasksByStatusSortedByDeadline(TaskStatus status) {
        List<Task> tasks = taskRepository.findByStatusOrderByDeadlineAsc(status);

        if (tasks.isEmpty()) {
            throw new RuntimeException("No tasks found for the given status");
        }
        return mapTaskListToTaskResponseDtoList(tasks);
    };

    @Override
    public List<TaskResponseDto> getTasksByStatusSortedByImportance(TaskStatus status) {
        List<Task> tasks = taskRepository.findByStatusOrderByImportanceDesc(status);
        if (tasks.isEmpty()) {
            throw new RuntimeException("No tasks found for the given status");
        }
        return mapTaskListToTaskResponseDtoList(tasks);
    }


}









