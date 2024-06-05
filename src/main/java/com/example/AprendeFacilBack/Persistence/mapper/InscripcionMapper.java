package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Inscripcion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InscripcionMapper implements RowMapper<Inscripcion> {
    @Override
    public Inscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
