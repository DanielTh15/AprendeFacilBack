package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Asignatura;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AsignaturaMapper implements RowMapper<Asignatura> {
    @Override
    public Asignatura mapRow(ResultSet rs, int rowNum) throws SQLException {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(rs.getInt("id"));
        asignatura.setNombre(rs.getString("nombre"));
        return asignatura;
    }
}
