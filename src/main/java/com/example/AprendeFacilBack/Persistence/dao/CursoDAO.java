package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;

import java.util.List;

public interface CursoDAO {

    List<Curso> getAll();

    Curso save(Curso curso);

    List<String> getAllNamesFromCurso();

    Curso getCursoById(Integer id) throws AprendoFacilCustomException;
}
