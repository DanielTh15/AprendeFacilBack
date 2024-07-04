package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.Tema;
import com.example.AprendeFacilBack.Domain.services.TemaService;
import com.example.AprendeFacilBack.Util.ApiResponse;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/tema")
public class TemaController {

    private final Logger log = Logger.getLogger(TemaController.class.getName());
    private final TemaService temaService;

    private final ObjectMapper objectMapper;
    @Autowired
    public TemaController(TemaService temaService, ObjectMapper objectMapper) {
        this.temaService = temaService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tema>> getAll() throws AprendoFacilCustomException {
        return ResponseEntity.ok(temaService.getAll());
    }

    @GetMapping("/get-by-id/{idTopic}")
    public ResponseEntity<ApiResponse> getTopiById(@PathVariable(value = "idTopic") Integer idTopic) throws AprendoFacilCustomException {
        Tema tema = temaService.getByIdTopic(idTopic);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Topic Found", tema));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestParam(value = "tema") String tema,
                                            @RequestParam(value = "resource")MultipartFile resource) throws JsonProcessingException {
        Tema temaDTO = this.objectMapper.readValue(tema, Tema.class);
        Tema temaSaved = temaService.save(temaDTO, resource);
        log.severe("Request for save topic: " + tema);
        return ResponseEntity.ok(new ApiResponse("Topic saved", temaSaved));
    }

    @GetMapping("/get-by-id-course/{idCourse}")
    private ResponseEntity<List<Tema>> geById(@PathVariable Integer idCourse) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(temaService.getById(idCourse));
    }
}
