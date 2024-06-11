package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Pregunta;
import com.example.AprendeFacilBack.Domain.dto.PreguntaAbierta;
import com.example.AprendeFacilBack.Persistence.mapper.PreguntaMapperAbierta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PreguntaAbiertaDAOImp extends PreguntaDAO<PreguntaAbierta> implements Pregunta_AbiertaDAO{

    private static final String select = "SELECT * FROM pregunta p INNER JOIN pre_abierta pa ON p.id = pa.id_pregunta";

    public PreguntaAbiertaDAOImp(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<PreguntaAbierta> list() {
        return jdbcTemplate.query(select, new PreguntaMapperAbierta());
    }

    @Override
    public PreguntaAbierta getById(Integer id) {
        return null;
    }

    @Override
    public PreguntaAbierta save(PreguntaAbierta pregunta) {
        return null;
    }

    @Override
    public PreguntaAbierta update(PreguntaAbierta pregunta) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
