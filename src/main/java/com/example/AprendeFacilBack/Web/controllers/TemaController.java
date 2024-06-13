package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.Tema;
import com.example.AprendeFacilBack.Domain.services.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tema")
public class TemaController {

    private final TemaService temaService;
    @Autowired
    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tema>> getAll(){
        return ResponseEntity.ok(temaService.getAll());
    }
    @PostMapping("/save")
    public ResponseEntity<Tema> save(@RequestBody Tema tema){
        return ResponseEntity.ok(temaService.save(tema));
    }
}
