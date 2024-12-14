package com.example.springboottest.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "User Entity")
public class User {
    @Schema(description = "유니크 ID", example = "1")
    private Long id;

    @Schema(description = "이름", example = "lee")
    private String name;

    @Schema(description = "이메일", example = "lee@google.com")
    private String email;
}