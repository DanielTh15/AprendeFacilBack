package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Inscripcion;
import com.example.AprendeFacilBack.Persistence.dao.InscripcionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncripcionServiceImp implements  InscripcionService{

    private final InscripcionDAO inscripcionDAO;

    @Autowired
    public IncripcionServiceImp(InscripcionDAO inscripcionDAO) {
        this.inscripcionDAO = inscripcionDAO;
    }

    @Override
    public List<Inscripcion> getAll() {
        return null;
    }
}
