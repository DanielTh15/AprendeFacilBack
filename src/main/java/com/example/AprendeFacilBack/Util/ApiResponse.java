package com.example.AprendeFacilBack.Util;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponse {
    private  String message;

    private LocalDateTime localDateTime;

    public ApiResponse(String message, LocalDateTime localDateTime) {
        this.message = message;
        this.localDateTime = localDateTime;
    }
}
