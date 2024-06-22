package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Pregunta;
import com.example.AprendeFacilBack.Domain.dto.PreguntaCerrada;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class Pregunta_CerradaDAOImp extends PreguntaDAO<PreguntaCerrada> implements Pregunta_CerradaDAO {

    private static final String insertTablaPadre = "INSERT INTO pregunta (tipo_pregunta, enunciado, id_tema) VALUES (?,?,?)";
    private static final String insertHija = "INSERT INTO pre_cerrada (id_pregunta, opcion, es_correcta)";
    private static final String select = "select * from pregunta p INNER JOIN pre_cerrada pc ON p.id = pc.id_pregunta";
    private static final String update = "UPDATE pregunta SET ";
    private static final String delete = "";

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
