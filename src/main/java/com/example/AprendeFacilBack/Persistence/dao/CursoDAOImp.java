package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Persistence.mapper.CursoMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CursoDAOImp implements CursoDAO{

    private static final String select = "SELECT * FROM curso";

    JdbcTemplate jdbcTemplate;

    public CursoDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Curso> getAll() {
        return jdbcTemplate.query(select, new CursoMapper());
    }
}
