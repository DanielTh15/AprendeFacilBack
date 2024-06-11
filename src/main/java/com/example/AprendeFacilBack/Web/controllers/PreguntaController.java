package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.PreguntaAbierta;
import com.example.AprendeFacilBack.Domain.services.PreguntaService;
import com.example.AprendeFacilBack.Domain.services.StrategyPattern.PreguntaAbiertaEstrategy;
import com.example.AprendeFacilBack.Domain.services.StrategyPattern.PreguntaCerradaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pregunta")
public class PreguntaController {

    private final PreguntaService preguntaService;
    private final PreguntaAbiertaEstrategy preguntaAbiertaEstrategy;
    private final PreguntaCerradaStrategy preguntaCerradaStrategy;

    @Autowired
    public PreguntaController(PreguntaService preguntaService, PreguntaAbiertaEstrategy preguntaAbiertaEstrategy, PreguntaCerradaStrategy preguntaCerradaStrategy) {
        this.preguntaService = preguntaService;
        this.preguntaAbiertaEstrategy = preguntaAbiertaEstrategy;
        this.preguntaCerradaStrategy = preguntaCerradaStrategy;
    }


    @GetMapping("/abierta/all")
    public ResponseEntity<List<PreguntaAbierta>> PreguntasAbiertas(){
        preguntaService.setStrategy(preguntaAbiertaEstrategy);
        return ResponseEntity.ok(preguntaAbiertaEstrategy.list());
    }
}
