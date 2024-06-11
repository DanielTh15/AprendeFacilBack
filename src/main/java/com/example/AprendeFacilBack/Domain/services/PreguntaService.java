package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Pregunta;
import com.example.AprendeFacilBack.Domain.services.StrategyPattern.PreguntaStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaService{

    private PreguntaStrategy<? extends Pregunta> strategy;

    public void setStrategy(PreguntaStrategy<? extends Pregunta> strategy){
        this.strategy = strategy;
    }

    public List<? extends Pregunta> list(){
        return strategy.list();
    }

}
