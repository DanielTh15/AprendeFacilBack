package com.example.AprendeFacilBack.Domain.dto;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Curso {

  private Integer id;
  private String name;
  private String decription;
  private Integer id_asignatura;
  private Float calificacion;

  public void setCursoFromRs(ResultSet rs) throws SQLException {

  }

}
