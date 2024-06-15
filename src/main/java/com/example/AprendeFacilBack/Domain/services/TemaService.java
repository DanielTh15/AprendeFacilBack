package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Tema;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TemaService {

    List<Tema> getAll() throws AprendoFacilCustomException;
    List<Tema> lookByName(String name);

    List<Tema> getById(Integer id) throws Exception;

    Tema getByIdTopic(Integer idTopic) throws AprendoFacilCustomException;

    Tema save(Tema tema, MultipartFile resource);

    void deleteBy(Integer id);
    Tema update(Tema tema);

}
