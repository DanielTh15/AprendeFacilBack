package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CursoMapper implements RowMapper<Curso> {
    @Override
    public Curso mapRow(ResultSet resultSet, int i) throws SQLException {
      Curso curso = new Curso();
        curso.setId(resultSet.getInt("id"));
        curso.setName(resultSet.getString("nombre"));
        curso.setDecription(resultSet.getString("descripcion"));
        curso.setId_asignatura(resultSet.getInt("id_asignatura"));
        curso.setCalificacion(resultSet.getFloat("calificacion"));
      return curso;
   }

}
