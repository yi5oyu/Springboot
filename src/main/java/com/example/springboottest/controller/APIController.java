package com.example.springboottest.controller;

import com.example.springboottest.service.NaverAPIService;
import com.example.springboottest.service.KakaoAPIService;
import com.example.springboottest.service.SKOpenAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private NaverAPIService naverAPIService;

    @Autowired
    private KakaoAPIService kakaoAPIService;

    @Autowired
    private SKOpenAPIService skOpenAPIService;

    @GetMapping(value = "/naver", produces ="application/json; charset=UTF-8")
    public ResponseEntity<String> naverAPI(){
        return naverAPIService.naverLocalSearch();
    }

    @GetMapping(value = "/kakao", produces ="application/json; charset=UTF-8")
    public ResponseEntity<String> kakaoAPI(){
        return kakaoAPIService.kakaoCategorySearch();
    }

    @GetMapping(value = "/sk", produces ="application/json; charset=UTF-8")
    public ResponseEntity<String> skAPI() throws IOException, InterruptedException {
        return skOpenAPIService.getTransit();
    }
}
