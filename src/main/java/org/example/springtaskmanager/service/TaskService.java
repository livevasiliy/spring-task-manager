package org.example.springtaskmanager.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.springtaskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.springtaskmanager.model.Task;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }


    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Long id, Task updatedTask) {
        Task task = getById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        task.setStatus(updatedTask.getStatus());
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        Task task = getById(id);
        taskRepository.delete(task);
    }
}
