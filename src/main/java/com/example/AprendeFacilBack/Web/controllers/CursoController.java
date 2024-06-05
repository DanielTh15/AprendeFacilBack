package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Domain.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Curso>> getAll(){
       return ResponseEntity.ok(cursoService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Curso> save(@RequestBody Curso curso){
        return ResponseEntity.ok(cursoService.save(curso));
    }

}
