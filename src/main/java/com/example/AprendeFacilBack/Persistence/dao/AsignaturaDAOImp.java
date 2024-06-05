package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Asignatura;
import com.example.AprendeFacilBack.Persistence.mapper.AsignaturaMapper;
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
        return jdbcTemplate.query(select, new AsignaturaMapper());
    }

    @Override
    public Asignatura save(Asignatura asignatura) {
        return null;
    }

    @Override
    public Asignatura findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Asignatura update(Asignatura asignatura) {
        return null;
    }
}
