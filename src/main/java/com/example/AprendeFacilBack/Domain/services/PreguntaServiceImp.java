package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Persistence.dao.PreguntaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreguntaServiceImp implements PreguntaService{

    private final PreguntaDAO preguntaDAO;

    @Autowired
    public PreguntaServiceImp(PreguntaDAO preguntaDAO) {
        this.preguntaDAO = preguntaDAO;
    }
}
