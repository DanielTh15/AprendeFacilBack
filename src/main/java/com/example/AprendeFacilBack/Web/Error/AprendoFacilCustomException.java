package com.example.AprendeFacilBack.Web.Error;

import org.springframework.http.HttpStatus;

public class AprendoFacilCustomException extends Exception{
    private HttpStatus status;
    public AprendoFacilCustomException(String message, HttpStatus s) {
        super(message);
        this.status = s;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

