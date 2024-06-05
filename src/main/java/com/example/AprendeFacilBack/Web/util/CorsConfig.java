package com.example.AprendeFacilBack.Web.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
public class CorsConfig {
    @Bean
     CorsConfigurationSource corsConfigurationSource(){
         CorsConfiguration corsConfiguration = new CorsConfiguration();

         corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
         corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
         corsConfiguration.setAllowedHeaders(List.of("*"));
         corsConfiguration.setExposedHeaders(List.of("Authorization"));
         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
         source.registerCorsConfiguration("/**", corsConfiguration);

         return source;
     }


}
