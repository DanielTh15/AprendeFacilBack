package com.example.AprendeFacilBack.Domain.services.StrategyPattern;

import com.example.AprendeFacilBack.Domain.dto.PreguntaCerrada;
import com.example.AprendeFacilBack.Persistence.dao.Pregunta_CerradaDAOImp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaCerradaStrategy implements PreguntaStrategy<PreguntaCerrada> {

    private final Pregunta_CerradaDAOImp preguntaCerradaDAOImp;

    public PreguntaCerradaStrategy(Pregunta_CerradaDAOImp preguntaCerradaDAOImp) {
        this.preguntaCerradaDAOImp = preguntaCerradaDAOImp;
    }

    @Override
    public List<PreguntaCerrada> list() {
        return preguntaCerradaDAOImp.list();
    }

    @Override
    public PreguntaCerrada getById(Integer id) {
        return preguntaCerradaDAOImp.getById(id);
    }

    @Override
    public PreguntaCerrada save(PreguntaCerrada pregunta) {
        return preguntaCerradaDAOImp.save(pregunta);

    }

    @Override
    public PreguntaCerrada update(PreguntaCerrada pregunta) {
        return preguntaCerradaDAOImp.update(pregunta);
    }

    @Override
    public void delete(Integer id) {
      preguntaCerradaDAOImp.delete(id);
    }
}
