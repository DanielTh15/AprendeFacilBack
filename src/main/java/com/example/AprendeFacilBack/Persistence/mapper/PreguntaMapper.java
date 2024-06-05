package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Pregunta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PreguntaMapper implements RowMapper<Pregunta> {
    @Override
    public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
