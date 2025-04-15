package com.ai.summarizer.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus; 
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class SummaryController {

    private static final String HUGGINGFACE_API_URL = "https://api-inference.huggingface.co/models/facebook/bart-large-cnn";
    private static final String HF_API_KEY = ""; // ✅ 여기에 붙여넣기 키값이여서 삭제

    @PostMapping("/summarize")
    public ResponseEntity<Map<String, String>> summarize(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        Map<String, String> result = new HashMap<>();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", HF_API_KEY);

            Map<String, Object> body = new HashMap<>();
            body.put("inputs", text);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List> response = restTemplate.postForEntity(HUGGINGFACE_API_URL, entity, List.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String summary = (String) ((Map<?, ?>) response.getBody().get(0)).get("summary_text");
                result.put("summary", summary);
                return ResponseEntity.ok(result);
            } else {
                result.put("summary", "요약 실패 또는 응답 없음");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }

        } catch (Exception e) {
            result.put("summary", "오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
