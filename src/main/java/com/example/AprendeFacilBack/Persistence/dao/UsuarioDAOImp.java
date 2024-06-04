package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Persistence.mapper.UsuarioMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDAOImp implements UsuarioDAO{

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL = "SELECT " +
            "id, " +
            "nombre," +
            "apellido, " +
            "telefono," +
            "correo," +
            "bloqueado FROM usuario";


    private  static  final String INSERT = "INSERT INTO usuario (id, nombre, apellido, " +
            "telefono, correo, bloqueado, deshabilitado, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public UsuarioDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<UsuarioDTO> getAll(){
        return jdbcTemplate.query(SELECT_ALL, new UsuarioMapper());
    }

   @Override
    public void register(UsuarioDTO usuarioDTO){
        jdbcTemplate.update(INSERT, usuarioDTO.getId(),
                usuarioDTO.getName(),
                usuarioDTO.getLastName(),
                usuarioDTO.getPhone(),
                usuarioDTO.getEmail(),
                usuarioDTO.getLocked(),
                usuarioDTO.getDisable(),
                usuarioDTO.getPassword());
    }
}
