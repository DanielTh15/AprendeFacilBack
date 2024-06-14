package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Tema;

import java.util.List;

public interface TemaDAO {

    List<Tema> getAll();
    Tema lookByName(String name);

    List<Tema> getById(Integer id) throws Exception;

    Tema save(Tema tema);
    void deleteBy(Integer id);
    Tema update(Tema tema);

}
