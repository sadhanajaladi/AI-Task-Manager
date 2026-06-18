package com.aitask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aitask.dto.AIRequest;
import com.aitask.service.AIService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "http://localhost:5173")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/generate-description")
    public String generateDescription(@Valid @RequestBody AIRequest request) {

        return aiService.generateDescription(request.getTitle());
    }
}