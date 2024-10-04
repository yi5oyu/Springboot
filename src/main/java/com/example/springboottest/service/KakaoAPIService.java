package com.example.springboottest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class KakaoAPIService {

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    public ResponseEntity<String> kakaoCategorySearch() {

        URI uri = UriComponentsBuilder
                .fromUriString("https://dapi.kakao.com")
                .path("/v2/local/search/category.json")
                .queryParam("category_group_code", "FD6")
                .queryParam("x", 127.09185179212506)
                .queryParam("y", 37.26669999999977)
                .queryParam("radius", 5000)
                .queryParam("size", 15)
                .queryParam("page", 1)
                .queryParam("sort", "distance")
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("Authorization", "KakaoAK " + kakaoApiKey)
                .header("Content-Type", "application/json; charset=UTF-8")
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(req, String.class);

        String responseBody = responseEntity.getBody();

        return ResponseEntity.ok(responseBody);
    }
}
