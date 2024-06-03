package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Curso;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CursoMapper {

  public Curso mapRow(ResultSet resultSet, int i) throws SQLException {
      Curso curso = new Curso();

      return curso;
  }

}
