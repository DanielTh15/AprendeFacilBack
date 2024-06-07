package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Web.Config.AWSSecretManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JWTConfiguration {

    @Bean
    public Map<String, String> jwtKeys() {
        // Obtener las claves del AWS Secrets Manager
        String secretName = "my-jwt-keys"; // Reemplaza con el nombre de tu secreto
        return AWSSecretManager.getSecret(secretName);
    }

    @Bean
    @Primary
    public JWTServiceImp customJWTServiceImp(Map<String, String> jwtKeys) {
        return new JWTServiceImp(jwtKeys);
    }
}
