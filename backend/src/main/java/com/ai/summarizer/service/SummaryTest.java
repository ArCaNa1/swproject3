package com.ai.summarizer.service;

public class SummaryTest {
    public static void main(String[] args) {
        SummaryService service = new SummaryService();

        String sampleText = """
            Artificial intelligence is rapidly advancing in various fields.
            The development of natural language processing has led to new innovations in AI applications.
            However, challenges like bias and fairness still remain in modern AI systems.
            Researchers are working on improving model accuracy.
            With more data, AI systems continue to improve.
            """;

        String summary = service.summarize(sampleText);
        System.out.println("=== 요약 결과 ===");
        System.out.println(summary);
    }
}
