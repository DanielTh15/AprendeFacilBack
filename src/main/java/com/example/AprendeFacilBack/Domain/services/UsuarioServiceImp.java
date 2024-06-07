package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Persistence.dao.UsuarioDAO;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import org.springframework.http.HttpStatus;
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

   @Override
    public UsuarioDTO getUserById(String email) throws AprendoFacilCustomException {
        UsuarioDTO usuarioDTO = usuarioDAO.getUserByEmail(email);
        if(usuarioDTO == null){
            throw new AprendoFacilCustomException("User is not ready", HttpStatus.NOT_FOUND);
        }
        return usuarioDTO;
    }

}
