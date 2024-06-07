package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Login;
import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import com.nimbusds.jose.JOSEException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

public interface AuthService {
    void Register (UsuarioDTO usuarioDTO);


    HashMap<String, String> login(Login login) throws AprendoFacilCustomException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException;
}
