package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Domain.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UsuarioController {

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);


    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> getAll(){
        List<UsuarioDTO> usuarioDTOS = usuarioService.getAll();
        log.debug("Request to register user: {}", usuarioDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOS);
    }
}
