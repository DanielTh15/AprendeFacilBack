package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Tema;
import com.example.AprendeFacilBack.Persistence.dao.TemaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaServiceImp implements TemaService{

    private final TemaDAO temaDAO;

    @Autowired
    public TemaServiceImp(TemaDAO temaDAO) {
        this.temaDAO = temaDAO;
    }

    @Override
    public List<Tema> getAll() {
        return temaDAO.getAll();
    }

    @Override
    public List<Tema> lookByName(String name) {
        return null;
    }

    @Override
    public Tema save(Tema tema) {
        return temaDAO.save(tema);
    }

    @Override
    public void deleteBy(Integer id) {

    }

    @Override
    public Tema update(Tema tema) {
        return null;
    }
}
