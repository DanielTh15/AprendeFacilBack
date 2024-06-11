package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Pregunta;
import com.example.AprendeFacilBack.Domain.dto.PreguntaCerrada;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class Pregunta_CerradaDAOImp extends PreguntaDAO<PreguntaCerrada> implements Pregunta_CerradaDAO {


    public Pregunta_CerradaDAOImp(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<PreguntaCerrada> list() {
        return null;
    }

    @Override
    public PreguntaCerrada getById(Integer id) {
        return null;
    }

    @Override
    public PreguntaCerrada save(PreguntaCerrada pregunta) {
        return null;
    }

    @Override
    public PreguntaCerrada update(PreguntaCerrada pregunta) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }


}
