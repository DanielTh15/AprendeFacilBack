package com.example.AprendeFacilBack.Domain.dto;

import lombok.Data;

@Data
public class Pregunta {

    private Integer id;
    private String tipoPregunta;
    private String enunciado;
    private Integer tema;

}
