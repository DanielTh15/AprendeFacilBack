package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper implements RowMapper<UsuarioDTO> {
    @Override
    public UsuarioDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(resultSet.getString("id"));
        usuarioDTO.setName(resultSet.getString("nombre"));
        usuarioDTO.setLastName(resultSet.getString("apellido"));
        usuarioDTO.setPhone(resultSet.getString("telefono"));
        usuarioDTO.setEmail(resultSet.getString("correo"));
        usuarioDTO.setDisable(resultSet.getBoolean("deshabilitado"));
        usuarioDTO.setPassword(resultSet.getString("contraseña"));
        usuarioDTO.setLocked(resultSet.getBoolean("bloqueado"));
        return  usuarioDTO;
    }
}
