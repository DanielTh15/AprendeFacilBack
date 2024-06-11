package com.example.AprendeFacilBack.Web.Error;

import com.example.AprendeFacilBack.Web.Error.dto.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

@ControllerAdvice
public class DefaultControllerException extends ResponseStatusExceptionHandler {
    @ExceptionHandler(AprendoFacilCustomException.class)
    public ResponseEntity<ErrorMessage> handleAppException(AprendoFacilCustomException exception){
        ErrorMessage errorMessage = new ErrorMessage(exception.getStatus(), exception.getMessage());
        return new ResponseEntity<>(errorMessage, exception.getStatus());
    }

}
