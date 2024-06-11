package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Persistence.dao.CursoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Curso save(Curso curso) {
        return cursoDAO.save(curso);
    }

    @Override
    public List<Curso> getAllNameFromCurso(String q){
        List<Curso> cursosByName = cursoDAO.getAll();
        List<Curso> cursosSearching = new ArrayList<>();
        for (Curso cur: cursosByName){
            if (cur.getName().toLowerCase().contains(q.toLowerCase())){
                cursosSearching.add(cur);
            }
        }
        return cursosSearching;
    }
}
