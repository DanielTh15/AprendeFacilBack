package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Asignatura;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AsignaturaDAOImp implements AsignaturaDAO{

    private static final String select = "SELECT * FROM asignatura";

    JdbcTemplate jdbcTemplate;

    public AsignaturaDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Asignatura> getAll() {
        return null;
    }
}
