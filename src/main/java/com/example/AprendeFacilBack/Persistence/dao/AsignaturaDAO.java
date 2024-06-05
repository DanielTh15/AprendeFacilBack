package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Asignatura;

import java.util.List;

public interface AsignaturaDAO {

    List<Asignatura> getAll();
    Asignatura save(Asignatura asignatura);
    Asignatura findById(Integer id);
    void deleteById(Integer id);
    Asignatura update(Asignatura asignatura);

}
