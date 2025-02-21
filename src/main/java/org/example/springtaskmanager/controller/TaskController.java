package org.example.springtaskmanager.controller;

import org.example.springtaskmanager.model.Task;
import org.example.springtaskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping()
    public List<Task> getAllTasks()
    {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id)
    {
        return taskService.getById(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task)
    {
        return taskService.create(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task updatedTask)
    {
        return taskService.update(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
