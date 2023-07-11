package com.challenge.demo.controller.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpResponse {
    private String id;
    private LocalDate created;
    private LocalDate lastLogin;
    private String token;
    private boolean isActive;

    public SignUpResponse(String id, LocalDate created, LocalDate lastLogin, String token, boolean isActive) {
        this.id = id;
        this.created = created;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

}
