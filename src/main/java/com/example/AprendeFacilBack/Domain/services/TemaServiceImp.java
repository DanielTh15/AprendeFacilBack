package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Persistence.dao.TemaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaServiceImp implements TemaService{

    private final TemaDAO temaDAO;

    @Autowired
    public TemaServiceImp(TemaDAO temaDAO) {
        this.temaDAO = temaDAO;
    }
}
