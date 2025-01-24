package com.example.springboottest.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/api/user")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> response = new HashMap<>();
        response.put("user", principal.getAttributes());

        OAuth2AuthorizedClient authorizedClient =
            authorizedClientService.loadAuthorizedClient("github", principal.getName());

        if (authorizedClient != null) {
            response.put("accessToken", authorizedClient.getAccessToken().getTokenValue());
        } else {
            response.put("accessToken", "토큰x");
        }

        return response;
    }
}