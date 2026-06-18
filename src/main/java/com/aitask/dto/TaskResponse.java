package com.aitask.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private String priority;
    private LocalDate dueDate;
    private String status;
    private LocalDateTime createdAt;
}