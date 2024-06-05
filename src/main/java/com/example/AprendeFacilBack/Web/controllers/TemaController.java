package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.services.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tema")
public class TemaController {

    private final TemaService temaService;
    @Autowired
    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }
}
