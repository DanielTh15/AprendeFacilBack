package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;

import javax.swing.tree.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper implements RowMapper<UsuarioDTO> {
    @Override
    public UsuarioDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(resultSet.getString("id"));
    }
}
