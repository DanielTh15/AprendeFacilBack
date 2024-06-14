package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.Tema;
import com.example.AprendeFacilBack.Domain.services.TemaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/tema")
public class TemaController {

    private final Logger log = Logger.getLogger(TemaController.class.getName());
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
        log.severe("Request for save topic: " + tema);
        return ResponseEntity.ok(temaService.save(tema));
    }

    @GetMapping("/get-by-id/{idCourse}")
    private ResponseEntity<List<Tema>> geById(@PathVariable Integer idCourse) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(temaService.getById(idCourse));
    }
}
