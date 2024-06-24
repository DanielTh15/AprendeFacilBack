package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.PreguntaCerrada;

import java.util.List;

public interface Pregunta_CerradaDAO {

    List<PreguntaCerrada> saveList(List<PreguntaCerrada> preguntaCerradaList);

}
