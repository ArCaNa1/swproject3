package com.ai.summarizer.service;

import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    public String summarize(String text) {
        if (text == null || text.isBlank()) {
            return "요약할 텍스트가 없습니다.";
        }

        // 아주 간단한 요약 예시: 앞의 3문장 반환
        String[] sentences = text.split("(?<=[.!?])\\s+");
        StringBuilder summary = new StringBuilder();
        for (int i = 0; i < Math.min(sentences.length, 3); i++) {
            summary.append(sentences[i]).append(" ");
        }

        return summary.toString().trim();
    }
}
