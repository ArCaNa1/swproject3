package com.ai.summarizer.service;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaperProcessor {
    public static void processAndSavePapers(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray papersArray = jsonObject.getJSONArray("data");

        List<String[]> processedData = new ArrayList<>();
        processedData.add(new String[]{"paperId", "title", "abstract"}); // CSV 헤더 추가

        for (int i = 0; i < papersArray.length(); i++) {
            JSONObject paper = papersArray.getJSONObject(i);
            String paperId = paper.getString("paperId");
            String title = paper.getString("title");
            
            // **초록이 없는 경우 "N/A"로 설정**
            String abstractText = paper.isNull("abstract") ? "요약 없음" : paper.optString("abstract", "요약 없음");

            processedData.add(new String[]{paperId, title, abstractText});
        }

        saveToCSV(processedData);
    }

    private static void saveToCSV(List<String[]> data) {
        try (FileWriter writer = new FileWriter("papers.csv")) {
            for (String[] row : data) {
                writer.write(String.join(",", row) + "\n");
            }
            System.out.println("📁 논문 데이터를 CSV 파일로 저장 완료: papers.csv");
        } catch (IOException e) {
            System.out.println("❌ 파일 저장 중 오류 발생: " + e.getMessage());
        }
    }
}
