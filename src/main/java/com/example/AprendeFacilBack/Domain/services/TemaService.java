package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Tema;

import java.util.List;

public interface TemaService {

    List<Tema> getAll();
    List<Tema> lookByName(String name);
    Tema save(Tema tema);
    void deleteBy(Integer id);
    Tema update(Tema tema);

}
