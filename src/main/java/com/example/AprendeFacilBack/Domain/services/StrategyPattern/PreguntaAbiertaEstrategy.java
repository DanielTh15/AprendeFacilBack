package com.example.AprendeFacilBack.Domain.services.StrategyPattern;

import com.example.AprendeFacilBack.Domain.dto.PreguntaAbierta;
import com.example.AprendeFacilBack.Domain.services.StrategyPattern.PreguntaStrategy;
import com.example.AprendeFacilBack.Persistence.dao.PreguntaAbiertaDAOImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PreguntaAbiertaEstrategy implements PreguntaStrategy<PreguntaAbierta> {

    private final PreguntaAbiertaDAOImp preguntaAbiertaDAO;

    @Autowired
    public PreguntaAbiertaEstrategy(PreguntaAbiertaDAOImp preguntaAbiertaDAO) {
        this.preguntaAbiertaDAO = preguntaAbiertaDAO;
    }


    @Override
    public List<PreguntaAbierta> list() {
        return preguntaAbiertaDAO.list();
    }

    @Override
    public PreguntaAbierta getById(Integer id) {
        return preguntaAbiertaDAO.getById(id);

    }

    @Override
    public PreguntaAbierta save(PreguntaAbierta pregunta) {
        return preguntaAbiertaDAO.save(pregunta);

    }
    @Transactional
    @Override
    public PreguntaAbierta update(PreguntaAbierta pregunta) {
        return preguntaAbiertaDAO.update(pregunta);
    }

    @Override
    public void delete(Integer id) {
       preguntaAbiertaDAO.delete(id);
    }
}
