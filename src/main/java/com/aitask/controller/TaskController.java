package com.aitask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.aitask.dto.TaskRequest;
import com.aitask.entity.Task;
import com.aitask.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public String createTask(@Valid @RequestBody TaskRequest request,
                             Authentication authentication) {

        String email = authentication.getName();

        return taskService.createTask(request, email);
    }
    
    @GetMapping
    public List<Task> getAllTasks(Authentication authentication) {

        String email = authentication.getName();

        return taskService.getAllTasks(email);
    }
    
    @PutMapping("/{id}")
    public String updateTask(@PathVariable Long id,
                             @Valid @RequestBody TaskRequest request,
                             Authentication authentication) {

        String email = authentication.getName();
        
        System.out.println("Update API called");

        return taskService.updateTask(id, request, email);
    }
    
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id,
                             Authentication authentication) {

        String email = authentication.getName();

        return taskService.deleteTask(id, email);
    }
}