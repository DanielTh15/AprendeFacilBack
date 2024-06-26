package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.Asignatura;
import com.example.AprendeFacilBack.Domain.services.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/asignatura")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;
    @Autowired
    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Asignatura>> getAll(){
        return ResponseEntity.ok(asignaturaService.getAll());
    }
    @PostMapping("/save")
    public ResponseEntity<Asignatura> save(@RequestBody Asignatura asignatura){
        return ResponseEntity.ok(asignaturaService.save(asignatura));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> getOne(@PathVariable Integer id){
        return ResponseEntity.ok(asignaturaService.findById(id));
    }

}
