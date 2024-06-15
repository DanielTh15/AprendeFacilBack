package com.example.AprendeFacilBack.Domain.dto;

import lombok.Data;

@Data
public class Tema {

    private Integer id;
    private Integer idCurso;
    private String nombre;
    private String contenido;
    private String recurso;
    //En esta variable es la encargada del almacenar la url que nos da S3 para la visualizacion del recurso del tema
    private String urlResource;

}
