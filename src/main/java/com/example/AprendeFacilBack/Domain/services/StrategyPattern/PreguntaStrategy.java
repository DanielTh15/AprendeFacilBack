package com.example.AprendeFacilBack.Domain.services.StrategyPattern;

import com.example.AprendeFacilBack.Domain.dto.Pregunta;
import com.example.AprendeFacilBack.Domain.dto.PreguntaCerrada;

import java.util.List;

public interface PreguntaStrategy<T extends Pregunta> {

    List<T> list();
    T getById(Integer id);
    T save(T pregunta);
    T update(T pregunta);
    void delete(Integer id);

}
