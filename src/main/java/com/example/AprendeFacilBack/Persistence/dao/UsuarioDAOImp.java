package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Persistence.mapper.UsuarioMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDAOImp implements UsuarioDAO{

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL = "SELECT * FROM usuario";

    private static  final String FIND_BY_EMAIL = "SELECT * FROM usuario us WHERE us.correo = ?";


    private  static  final String INSERT = "INSERT INTO usuario (id, nombre, apellido, " +
            "telefono, correo, bloqueado, deshabilitado, contraseña) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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


    @Override
    public UsuarioDTO getUserByEmail(String email){
        UsuarioDTO usuarioDTO = jdbcTemplate.queryForObject(FIND_BY_EMAIL, new UsuarioMapper(), email);
        return usuarioDTO;
    }
}
