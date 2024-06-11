package com.example.AprendeFacilBack.Domain.services.StrategyPattern;

import com.example.AprendeFacilBack.Domain.dto.PreguntaCerrada;
import com.example.AprendeFacilBack.Domain.services.StrategyPattern.PreguntaStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaCerradaStrategy implements PreguntaStrategy<PreguntaCerrada> {
    @Override
    public List<PreguntaCerrada> list() {
        return null;
    }

    @Override
    public PreguntaCerrada getById(Integer id) {
        return null;
    }

    @Override
    public PreguntaCerrada save(PreguntaCerrada pregunta) {
        return null;
    }

    @Override
    public PreguntaCerrada update(PreguntaCerrada pregunta) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
