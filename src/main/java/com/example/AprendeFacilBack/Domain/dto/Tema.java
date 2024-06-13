package com.example.AprendeFacilBack.Domain.dto;

import lombok.Data;

@Data
public class Tema {

    private Integer id;
    private Integer idCurso;
    private String nombre;
    private String contenido;
    private String recurso;

}
