package com.example.springboottest.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String email;
}