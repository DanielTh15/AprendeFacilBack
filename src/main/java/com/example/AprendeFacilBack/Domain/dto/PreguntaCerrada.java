package com.example.AprendeFacilBack.Domain.dto;

import lombok.Data;

@Data
public class PreguntaCerrada extends Pregunta{

    private Integer id;
    private Integer id_pregunta;
    private String opcion;
    private boolean es_correcta;


}
