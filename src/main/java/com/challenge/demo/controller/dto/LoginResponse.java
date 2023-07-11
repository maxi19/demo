package com.challenge.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginResponse {

    private String id;
    private String created;
    private String lastLogin;
    private String token;
    private Boolean isActive;
    private String name;
    private String email;
    private String password;


}
