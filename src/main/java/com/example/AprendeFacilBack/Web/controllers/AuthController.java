package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.Login;
import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Domain.services.AuthService;
import com.example.AprendeFacilBack.Util.ApiResponse;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import com.nimbusds.jose.JOSEException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private Logger log = LoggerFactory.getLogger(AuthController.class);

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UsuarioDTO usuarioDTO) throws AprendoFacilCustomException {
        log.debug("Request to insert  user: {}", usuarioDTO);
        authService.Register(usuarioDTO);
        ApiResponse apiResponse = new ApiResponse("user created", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody Login login) throws AprendoFacilCustomException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
        log.debug("Request to login  user: {}", login);
        HashMap<String,String> loginData = authService.login(login);
        ApiResponse apiResponse = new ApiResponse(loginData.get("UserLoggedIn"), null);
        return ResponseEntity.status(HttpStatus.OK).header(
                HttpHeaders.AUTHORIZATION,"Bearer "+ loginData.get("token"))
                .body(apiResponse);
    }

    @PostMapping("/register-student")
    public ResponseEntity<ApiResponse> registerStudent(@RequestBody UsuarioDTO usuarioDTO) throws AprendoFacilCustomException {
        log.debug("Request to register a student: {}", usuarioDTO);
        UsuarioDTO studentSaved = authService.RegisterStudent(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Student created successfully", studentSaved));
    }

}
