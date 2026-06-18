package com.aitask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aitask.entity.Task;
import com.aitask.entity.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

}