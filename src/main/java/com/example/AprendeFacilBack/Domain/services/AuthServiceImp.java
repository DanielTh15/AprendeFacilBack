package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Persistence.dao.UsuarioDAO;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService{

    private UsuarioDAO usuarioDAO;

    public AuthServiceImp(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }


    @Override
    public void Register(UsuarioDTO usuarioDTO){
        usuarioDTO.setLocked(false);
        usuarioDTO.setDisable(false);
        usuarioDAO.register(usuarioDTO);
    }
}
