package com.example.AprendeFacilBack.Domain.services;

import com.nimbusds.jose.JOSEException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

public interface JWTService {
    String createToken(String emailUser) throws JOSEException, IOException, NoSuchAlgorithmException, InvalidKeySpecException;

    boolean isValid(String token) throws ParseException;

    //Extraemos el usuario del token
    String getUsername(String token) throws ParseException;
}
