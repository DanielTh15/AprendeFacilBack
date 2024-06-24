package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Asignatura;
import com.example.AprendeFacilBack.Domain.dto.Curso;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class CursoMapper implements RowMapper<Curso> {

    private boolean queryMetodoSelectALL = false;
    @Override
    public Curso mapRow(ResultSet resultSet, int i) throws SQLException {
      Curso curso = new Curso();
        curso.setId(resultSet.getInt("id"));
        curso.setName(resultSet.getString("nombre"));
        curso.setDecription(resultSet.getString("descripcion"));
        curso.setId_asignatura(resultSet.getInt("id_asignatura"));
        curso.setCalificacion(resultSet.getFloat("calificacion"));
        curso.setImage(resultSet.getString("img"));

        if (queryMetodoSelectALL){
            Asignatura asignatura = new Asignatura();
            asignatura.setId(resultSet.getInt("id_asignatura"));
            if (curso.getId_asignatura() != null && asignatura.getId() == curso.getId_asignatura()){
                asignatura.setNombre(resultSet.getString("nombre_asignatura"));
                curso.setAsignatura(asignatura);
            }
        }

        return curso;
    }





}
