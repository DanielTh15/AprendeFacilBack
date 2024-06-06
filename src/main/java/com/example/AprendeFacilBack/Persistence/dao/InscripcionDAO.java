package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Inscripcion;

import java.util.List;

public interface InscripcionDAO {

    List<Inscripcion> getAll();

    Inscripcion save(Inscripcion inscripcion);



}
