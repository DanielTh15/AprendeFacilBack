package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Domain.services.CursoService;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    private  static  final Logger log = Logger.getLogger(CursoController.class.getName());

    private final CursoService cursoService;
    private final ObjectMapper objectMapper;


    @Autowired
    public CursoController(CursoService cursoService, ObjectMapper objectMapper) {
        this.cursoService = cursoService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Curso>> getAll(){
       return ResponseEntity.ok(cursoService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Curso> save(@RequestParam String curso,
                                      @RequestParam MultipartFile image) throws JsonProcessingException {
        Curso cur = this.objectMapper.readValue(curso, Curso.class);
        log.severe("Request to save course: {}" + curso );
        cur.setCalificacion(12.4F);
        return ResponseEntity.ok(cursoService.save(cur, image));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) throws AprendoFacilCustomException {
        Curso curso = cursoService.getCursoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(curso);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<Curso>> searchCursosByName(@PathVariable(value = "query") String query){
        log.severe("Request recibida: " +  query);


        List<Curso> searchedCursos = cursoService.getAllNameFromCurso(query);
        return new ResponseEntity<>(searchedCursos, HttpStatus.OK);
    }
}
