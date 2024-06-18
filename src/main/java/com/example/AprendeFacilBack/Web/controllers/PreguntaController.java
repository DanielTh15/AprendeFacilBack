package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.PreguntaAbierta;
import com.example.AprendeFacilBack.Domain.services.PreguntaService;
import com.example.AprendeFacilBack.Domain.services.StrategyPattern.PreguntaAbiertaEstrategy;
import com.example.AprendeFacilBack.Domain.services.StrategyPattern.PreguntaCerradaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/pregunta")
public class PreguntaController {
    private final Logger logger = Logger.getLogger(PreguntaController.class.getName());
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
    public ResponseEntity<List<PreguntaAbierta>> getAll(){
        preguntaService.setStrategy(preguntaAbiertaEstrategy);
        return ResponseEntity.ok(preguntaAbiertaEstrategy.list());
    }

    @PostMapping("/abierta/save")
    public ResponseEntity<PreguntaAbierta> save(@RequestBody PreguntaAbierta preguntaAbierta){
        preguntaService.setStrategy(preguntaAbiertaEstrategy);
        return ResponseEntity.ok(preguntaAbiertaEstrategy.save(preguntaAbierta));
    }
    @PutMapping("/abierta/update")
    public ResponseEntity<PreguntaAbierta> update(@RequestBody PreguntaAbierta preguntaAbierta){
        logger.severe("datos:{}"+ preguntaAbierta);
        preguntaService.setStrategy(preguntaAbiertaEstrategy);
        return ResponseEntity.ok(preguntaAbiertaEstrategy.update(preguntaAbierta));
    }

}
