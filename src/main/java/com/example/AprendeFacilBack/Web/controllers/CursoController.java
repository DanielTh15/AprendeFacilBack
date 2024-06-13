package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Domain.services.CursoService;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    private  static  final Logger log = Logger.getLogger(CursoController.class.getName());

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
        log.severe("Request to save course: {}" + curso );
        curso.setCalificacion(12.4F);
        return ResponseEntity.ok(cursoService.save(curso));
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
