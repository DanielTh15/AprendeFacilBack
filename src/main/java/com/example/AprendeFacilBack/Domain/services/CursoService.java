package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface CursoService {

    List<Curso> getAll();

    Curso save(Curso curso, MultipartFile image);

    List<Curso> getAllNameFromCurso(String q);

    Curso getCursoById(Integer id) throws AprendoFacilCustomException;
}
