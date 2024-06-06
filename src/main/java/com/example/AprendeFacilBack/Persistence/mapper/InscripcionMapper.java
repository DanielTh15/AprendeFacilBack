package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Inscripcion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class InscripcionMapper implements RowMapper<Inscripcion> {
    @Override
    public Inscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Inscripcion inscripcion = new Inscripcion();
        Timestamp timestamp = rs.getTimestamp("fecha");
        // Conversion de timestamp a LocalDateTime
        LocalDateTime fechaInscripcion = timestamp.toLocalDateTime();
         inscripcion.setUsuario(rs.getString(""));
         inscripcion.setIdCurso(rs.getInt(""));
         inscripcion.setFecha(fechaInscripcion);
        return inscripcion;
    }
}
