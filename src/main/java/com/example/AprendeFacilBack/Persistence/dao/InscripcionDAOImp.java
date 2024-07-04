package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Inscripcion;
import com.example.AprendeFacilBack.Persistence.mapper.InscripcionMapper;
import com.example.AprendeFacilBack.Web.util.DAOutil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class InscripcionDAOImp implements InscripcionDAO{

    public static final String select = "SELECT * FROM inscripcion";
    public static final String insert = "INSERT INTO inscripcion () VALUES (?,?,?)";
    JdbcTemplate jdbcTemplate;

    public InscripcionDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Inscripcion> getAll() {
        return jdbcTemplate.query(select, new InscripcionMapper());
    }

    @Override
    public Inscripcion save(Inscripcion inscripcion) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insert);
            DAOutil.setPrepareStatement(ps, new Object[]{
                    inscripcion.getUsuario(),
                    inscripcion.getIdCurso(),
                    inscripcion.getFecha()
            });
            return ps;
        });
        return inscripcion;
    }
}
