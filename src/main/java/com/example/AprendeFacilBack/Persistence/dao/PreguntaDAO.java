package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Pregunta;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public abstract class PreguntaDAO<T extends Pregunta> {

     JdbcTemplate jdbcTemplate;

    public PreguntaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public abstract List<T> list();
    public abstract T getById(Integer id);
    public abstract T save(T pregunta);
    public abstract T update(T pregunta);
    public abstract void delete(Integer id);


}
