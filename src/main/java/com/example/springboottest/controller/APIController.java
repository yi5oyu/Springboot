package com.example.springboottest.controller;

import com.example.springboottest.service.NaverAPIService;
import com.example.springboottest.service.KakaoAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private NaverAPIService naverAPIService;

    @Autowired
    private KakaoAPIService kakaoAPIService;

    @GetMapping(value = "/naver", produces ="application/json; charset=UTF-8")
    public ResponseEntity<String> naverAPI(){
        return naverAPIService.naverLocalSearch();
    }

    @GetMapping(value = "/kakao", produces ="application/json; charset=UTF-8")
    public ResponseEntity<String> kakaoAPI(){
        return kakaoAPIService.kakaoCategorySearch();
    }
}
