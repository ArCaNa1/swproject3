package com.ai.summarizer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // <- Spring Boot가 REST API 컨트롤러로 인식하도록 함
@RequestMapping("/api")  // <- API의 기본 경로 설정
public class TestController {

    @GetMapping("/test")  // <- '/api/test' 경로로 GET 요청을 받음
    public String testAPI() {
        return "Spring Boot API 정상 작동 중!";
    }
}
