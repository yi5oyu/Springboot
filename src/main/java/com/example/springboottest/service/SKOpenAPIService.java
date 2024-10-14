package com.example.springboottest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;

@Service
public class SKOpenAPIService {
    @Value("${skopenapi.api.key}")
    private String apiKey;

    public ResponseEntity<String> getRoute() {
        URI uri = UriComponentsBuilder
                .fromUriString("https://apis.openapi.sk.com")
                .path("/tmap/routes")
                .queryParam("version", "1")
                .queryParam("startX", 126.9246033)
                .queryParam("startY", 33.45241976)
                .queryParam("endX", 126.9041895)
                .queryParam("endY", 33.4048969)
//                .queryParam("startName", "%EA%B4%91%EC%B9%98%EA%B8%B0%ED%95%B4%EB%B3%80")
//                .queryParam("endName", "%EC%98%A8%ED%8F%89%ED%8F%AC%EA%B5%AC")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

//        String requestBody = """
//        {
//            "startX": """ + 126.9246033 + """
//            ,"startY": """ + 33.45241976 + """
//            ,"endX": """ + 126.9041895 + """
//            ,"endY": """ + 33.4048969 + """
//            ,"startName": "출발지",
//            "endName": "도착지",
//            "searchOption": "10",
//            "sort": "custom"
//        }
//        """;

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("appKey", apiKey)
                .header("Content-Type", "application/json; charset=UTF-8")
                .build();



        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(req, String.class);

        return ResponseEntity.ok(responseEntity.getBody());
    }

    public ResponseEntity<String> getTransit() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://apis.openapi.sk.com/transit/routes/sub"))
                .header("accept", "application/json")
                .header("appKey", apiKey)
                .header("content-type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(
                        "{\"startX\":\"126.926493082645\"," +
                                "\"startY\":\"37.6134436427887\"," +
                                "\"endX\":\"127.126936754911\"," +
                                "\"endY\":\"37.5004198786564\"}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return ResponseEntity.ok(response.body());
    }
}
