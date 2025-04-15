// PdfController.java
package com.ai.summarizer.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PdfController {

    private static final String HUGGINGFACE_API_URL = "https://api-inference.huggingface.co/models/facebook/bart-large-cnn";
    private static final String HF_API_KEY = ""; // 새 키로 설정

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> handleFileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, String> result = new HashMap<>();

        try {
            // PDF → 텍스트 추출
            PDDocument document = PDDocument.load(file.getInputStream());
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String extractedText = pdfStripper.getText(document);
            document.close();

            // Hugging Face 요약 API 요청
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", HF_API_KEY);

            Map<String, Object> body = new HashMap<>();
            body.put("inputs", extractedText);

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
            result.put("summary", "서버 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
