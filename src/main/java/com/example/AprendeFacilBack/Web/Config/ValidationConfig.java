package com.example.AprendeFacilBack.Web.Config;

import com.example.AprendeFacilBack.Domain.Validations.AuthValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ValidationConfig {

    @Bean
    public AuthValidation authValidation(){
        return new AuthValidation();
    }
}
