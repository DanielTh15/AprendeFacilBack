package com.example.AprendeFacilBack.Util;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiResponse {
    private  String message;


    public ApiResponse(String message) {
        this.message = message;
    }
}
