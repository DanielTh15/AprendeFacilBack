package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Asignatura;
import com.example.AprendeFacilBack.Persistence.dao.AsignaturaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImp implements AsignaturaService{

    private final AsignaturaDAO asignaturaDAO;

    @Autowired
    public AsignaturaServiceImp(AsignaturaDAO asignaturaDAO) {
        this.asignaturaDAO = asignaturaDAO;
    }

    @Override
    public List<Asignatura> getAll() {
        return asignaturaDAO.getAll();
    }
}
