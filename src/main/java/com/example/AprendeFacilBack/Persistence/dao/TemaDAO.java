package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Tema;

import java.util.List;

public interface TemaDAO {

    List<Tema> getAll();
    Tema lookByName(String name);

    List<Tema> getByIdCourse(Integer id) throws Exception;

    Tema getTopicById(Integer idTopic);

    Tema save(Tema tema);
    void deleteBy(Integer id);
    Tema update(Tema tema);

}
