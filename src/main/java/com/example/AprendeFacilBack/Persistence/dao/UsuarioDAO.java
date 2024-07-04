package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioDAO {
    List<UsuarioDTO> getAll();

    UsuarioDTO register(UsuarioDTO usuarioDTO);
    boolean existUserById(String id);

    UsuarioDTO getUserByEmail(String email);
}
