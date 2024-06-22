package com.example.AprendeFacilBack.Domain.dto;

import lombok.Data;

@Data
public class PreguntaAbierta extends Pregunta{

   private Integer id_tabla_hija;
   private Integer id_pregunta;
   private String respuesta_correcta;

}
