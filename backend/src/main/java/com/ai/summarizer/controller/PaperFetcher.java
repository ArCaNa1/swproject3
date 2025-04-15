package com.ai.summarizer.controller;

import com.ai.summarizer.service.PaperProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class PaperFetcher {

    @GetMapping("/papers")
    public String fetchPapers() {
        String apiUrl = "https://api.semanticscholar.org/graph/v1/paper/search?query=AI&limit=5&fields=title,abstract"; // 초록 추가
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // 서버 부하 방지를 위해 1초 대기 (429 오류 방지)
            Thread.sleep(4000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // JSON 데이터를 가공하여 CSV로 저장
            PaperProcessor.processAndSavePapers(response.toString());

            return response.toString();

        } catch (Exception e) {
            return "❌ 논문 데이터를 가져오는 중 오류 발생: " + e.getMessage();
        }
    }
}
