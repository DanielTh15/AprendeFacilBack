package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> getAll();

    UsuarioDTO getUserById(String email) throws AprendoFacilCustomException;
}
