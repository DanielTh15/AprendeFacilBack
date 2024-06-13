package com.example.AprendeFacilBack.Web.Error;

import org.springframework.http.HttpStatus;

public class AprendoFacilCustomException extends Exception{
    private HttpStatus status;
    public AprendoFacilCustomException(String message, HttpStatus s) {
        super(message);
        this.status = s;
    }
    public  AprendoFacilCustomException(String message){
        super(message);
        this.status = null;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

