package com.aitask.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();

    public String generateDescription(String title) {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

        String requestBody = """
        {
          "contents": [
            {
              "parts": [
                {
                  "text": "Generate a professional task description for: %s"
                }
              ]
            }
          ]
        }
        """.formatted(title);

        String response = restClient.post()
                .uri(url)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .retrieve()
                .body(String.class);

        return response;
    }
}