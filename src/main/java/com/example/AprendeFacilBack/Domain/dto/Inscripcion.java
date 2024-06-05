package com.example.AprendeFacilBack.Domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Inscripcion {

    private String usuario;
    private Integer idCurso;
    private LocalDateTime fecha;

}
