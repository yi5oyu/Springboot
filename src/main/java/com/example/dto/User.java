package com.example.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
}
