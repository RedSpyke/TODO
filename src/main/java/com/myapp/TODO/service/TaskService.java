package com.myapp.TODO.service;

import com.myapp.TODO.dto.TaskDTO;
import com.myapp.TODO.model.Task;
import com.myapp.TODO.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<TaskDTO> getTaskById(UUID id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(taskMapper::toDTO);
    }

    public TaskDTO saveTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDTO(savedTask);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}