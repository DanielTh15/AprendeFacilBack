package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Persistence.mapper.UsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDAOImp implements UsuarioDAO {

    private static final Logger log = LoggerFactory.getLogger(UsuarioDAOImp.class);

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL = "SELECT * FROM usuario";

    private static final String FIND_BY_EMAIL = "SELECT * FROM usuario us WHERE us.correo = ?";

    private static final String FIND_BY_ID = "SELECT 1 FROM usuario us WHERE us.id = ? LIMIT 1";

    private static final String INSERT = "INSERT INTO usuario (id, nombre, apellido, " +
            "telefono, correo, bloqueado, deshabilitado, contrasena, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public UsuarioDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<UsuarioDTO> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new UsuarioMapper());
    }

    @Override
    public UsuarioDTO register(UsuarioDTO usuarioDTO) {
        jdbcTemplate.update(INSERT, usuarioDTO.getId(),
                usuarioDTO.getName(),
                usuarioDTO.getLastName(),
                usuarioDTO.getPhone(),
                usuarioDTO.getEmail(),
                usuarioDTO.getLocked(),
                usuarioDTO.getDisable(),
                usuarioDTO.getPassword(),
                usuarioDTO.getRol());
        return usuarioDTO;
    }


    @Override
    public  boolean existUserById(String id){
        try {
            int result =  jdbcTemplate.queryForObject(FIND_BY_ID, Integer.class, id);
            return  result > 0;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }



    @Override
    public UsuarioDTO getUserByEmail(String email){
        UsuarioDTO usuarioDTO = null;
        try {
            usuarioDTO = jdbcTemplate.queryForObject(FIND_BY_EMAIL, new UsuarioMapper(), email);
            log.debug("user obtained from database: {}", usuarioDTO);
        } catch (EmptyResultDataAccessException e) {
            log.debug("No user found with email: {}", email);
        } catch (IncorrectResultSizeDataAccessException e) {
            log.error("Multiple users found with email: {}", email);
        }
        return usuarioDTO;
    }

}

