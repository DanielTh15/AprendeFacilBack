package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Persistence.dao.UsuarioDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService{

    private UsuarioDAO usuarioDAO;

    public UsuarioServiceImp(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }


    @Override
    public List<UsuarioDTO> getAll(){
        return usuarioDAO.getAll();
    }

}
