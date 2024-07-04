package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.Validations.AuthValidation;
import com.example.AprendeFacilBack.Domain.dto.Login;
import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Persistence.dao.UsuarioDAO;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import com.nimbusds.jose.JOSEException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

@Service
public class AuthServiceImp implements AuthService{

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImp.class);
    private final UsuarioDAO usuarioDAO;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthValidation authValidation;

    @Autowired
    public AuthServiceImp(UsuarioDAO usuarioDAO, AuthenticationManager authenticationManager, JWTService jwtService,
                          PasswordEncoder passwordEncoder, AuthValidation authValidation) {
        this.usuarioDAO = usuarioDAO;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authValidation = authValidation;
    }

    @Override
    public void Register(UsuarioDTO usuarioDTO) throws AprendoFacilCustomException {
        //Validamos campos antes de registrarnos
        authValidation.ValidateRegisterUser(usuarioDTO);
        boolean resultUserById = usuarioDAO.existUserById(usuarioDTO.getId());
        log.debug("Result of search user exist by id",  resultUserById);
        if (resultUserById){
            throw new AprendoFacilCustomException("This user us ready, please use other id", HttpStatus.BAD_REQUEST);
        }

        UsuarioDTO existUser = usuarioDAO.getUserByEmail(usuarioDTO.getEmail());
        if (existUser != null){
            throw new AprendoFacilCustomException("This user is ready, please use other email", HttpStatus.BAD_REQUEST);
        }

        String password = passwordEncoder.encode(usuarioDTO.getPassword());
        usuarioDTO.setPassword(password);
        usuarioDTO.setLocked(false);
        usuarioDTO.setDisable(false);
        usuarioDTO.setRol("DOCENT");
        usuarioDAO.register(usuarioDTO);
    }


    @Override
    public HashMap<String, String> login(Login login) throws AprendoFacilCustomException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
        UsuarioDTO usuarioDTO = usuarioDAO.getUserByEmail(login.getEmail());
        log.debug("Service to login: {}", usuarioDTO);
        if(usuarioDTO==null){
            throw new AprendoFacilCustomException("Password or email are incorrect", HttpStatus.NOT_FOUND);
        }

        UsernamePasswordAuthenticationToken loginUser = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        Authentication authentication;
        try {
            authentication = this.authenticationManager.authenticate(loginUser);
        } catch (AuthenticationException e) {
            throw new AprendoFacilCustomException("Password or email are incorrect", HttpStatus.BAD_REQUEST);
        }
        String token = jwtService.createToken(login.getEmail());
        User userLoggedIn = (User) authentication.getPrincipal();

        HashMap<String, String> loginData = new HashMap<>();
        loginData.put("token", token);
        loginData.put("UserLoggedIn", userLoggedIn.getUsername());
        return loginData;
    }

    @Override
    public UsuarioDTO RegisterStudent(UsuarioDTO usuarioDTO) throws AprendoFacilCustomException {
        authValidation.ValidateRegisterUser(usuarioDTO);

        UsuarioDTO studentByEmail = usuarioDAO.getUserByEmail(usuarioDTO.getEmail());
        if (studentByEmail !=null){
            throw new AprendoFacilCustomException("This account is ready, please use other email", HttpStatus.BAD_REQUEST);
        }

        boolean studentById = usuarioDAO.existUserById(usuarioDTO.getId());
        if (studentById){
            throw new AprendoFacilCustomException("This account is ready, please use other id", HttpStatus.BAD_REQUEST);
        }

        //Encriptamos la contrase y enviamos otros datos.
        String passwordEncoded = passwordEncoder.encode(usuarioDTO.getPassword());
        usuarioDTO.setPassword(passwordEncoded);
        usuarioDTO.setLocked(false);
        usuarioDTO.setDisable(false);
        usuarioDTO.setRol("STUDENT");
        return usuarioDAO.register(usuarioDTO);
    }
}
