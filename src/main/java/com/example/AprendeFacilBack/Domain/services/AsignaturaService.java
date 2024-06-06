package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Asignatura;

import java.util.List;

public interface AsignaturaService {

    List<Asignatura> getAll();

    Asignatura save(Asignatura asignatura);
    Asignatura findById(Integer id);
    void deleteById(Integer id);
    Asignatura update(Asignatura asignatura);

}
