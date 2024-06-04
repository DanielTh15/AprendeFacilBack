package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Persistence.dao.CursoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImp implements CursoService{

    private final CursoDAO cursoDAO;

    @Autowired
    public CursoServiceImp(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    @Override
    public List<Curso> getAll() {
        return cursoDAO.getAll();
    }
}
