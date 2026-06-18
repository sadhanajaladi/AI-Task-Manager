package com.aitask.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aitask.dto.TaskRequest;
import com.aitask.entity.Task;
import com.aitask.entity.User;
import com.aitask.repository.TaskRepository;
import com.aitask.repository.UserRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Task
    public String createTask(TaskRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setStatus(request.getStatus());
        task.setCreatedAt(LocalDateTime.now());
        task.setUser(user);

        taskRepository.save(task);

        return "Task Created Successfully";
    }

    // View All Tasks
    public List<Task> getAllTasks(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByUser(user);
    }
    
    public String updateTask(Long id, TaskRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Ensure the logged-in user owns this task
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access Denied");
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setStatus(request.getStatus());

        taskRepository.save(task);

        return "Task Updated Successfully";
    }
    
    public String deleteTask(Long id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access Denied");
        }

        taskRepository.delete(task);

        return "Task Deleted Successfully";
    }
}