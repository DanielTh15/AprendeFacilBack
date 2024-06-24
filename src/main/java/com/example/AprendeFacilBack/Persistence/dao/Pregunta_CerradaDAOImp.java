package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.PreguntaCerrada;
import com.example.AprendeFacilBack.Persistence.mapper.PreguntaMapperCerrada;
import com.example.AprendeFacilBack.Web.util.DAOutil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
@Repository
public class Pregunta_CerradaDAOImp extends PreguntaDAO<PreguntaCerrada> implements Pregunta_CerradaDAO {

    private static final String insertTablaPadre = "INSERT INTO pregunta (tipo_pregunta, enunciado, id_tema) VALUES (?,?,?)";
    private static final String insertHija = "INSERT INTO pre_cerrada (id_pregunta, opcion, es_correcta) VALUES (?,?,?)";
    private static final String select = "select * from pregunta p INNER JOIN pre_cerrada pc ON p.id = pc.id_pregunta";
    private static final String selectById = select + " WHERE id = ?";
    private static final String updatePadre = "UPDATE pregunta SET tipo_pregunta = ?, enunciado = ?, id_tema = ? WHERE id = ?";
    private static final String updateHija = "UPDATE pre_cerrada SET opcion = ?, es_correcta = ? WHERE id_hija = ?";
    private static final String delete = "DELETE FROM pregunta WHERE id = ?";

    public Pregunta_CerradaDAOImp(JdbcTemplate jdbcTemplate) {

        super(jdbcTemplate);
    }

    @Override
    public List<PreguntaCerrada> list() {
        return jdbcTemplate.query(select, new PreguntaMapperCerrada());

    }

    @Override
    public PreguntaCerrada getById(Integer id) {
        return jdbcTemplate.queryForObject(selectById, new PreguntaMapperCerrada(), id);
    }

    @Override
    public PreguntaCerrada save(PreguntaCerrada pregunta) {

        KeyHolder key = new GeneratedKeyHolder();
        KeyHolder key1 = new GeneratedKeyHolder();
        jdbcTemplate.update(consulta -> {
            PreparedStatement ps = consulta.prepareStatement(insertTablaPadre, new String[]{"id"});
            DAOutil.setPrepareStatement(ps, new Object[]{
                    pregunta.getTipoPregunta(),
                    pregunta.getEnunciado(),
                    pregunta.getTema()

            });
            return ps;
        }, key);

        pregunta.setId((Integer) key.getKey());

        System.out.println(  pregunta.getTipoPregunta() + "\n" +pregunta.getEnunciado()+ "\n" + pregunta.getTema() + "\n" +
                pregunta.getOpcion()+"\n"+ pregunta.getId() +"\n"+ pregunta.isEs_correcta() );


        jdbcTemplate.update(consulta1 -> {
            PreparedStatement ps1 = consulta1.prepareStatement(insertHija, new String[]{"id_hija"});
            DAOutil.setPrepareStatement(ps1, new Object[]{
                    pregunta.getId(),
                    pregunta.getOpcion(),
                    pregunta.isEs_correcta()

            });
            return ps1;
        }, key1);

        return pregunta;
    }


    @Override
    public PreguntaCerrada update(PreguntaCerrada pregunta) {

        jdbcTemplate.update(updatePadre,
                pregunta.getTipoPregunta(),
                pregunta.getEnunciado(),
                pregunta.getTema(),
                pregunta.getId()
        );
        jdbcTemplate.update(updateHija,
                pregunta.getOpcion(),
                pregunta.isEs_correcta(),
                pregunta.getId()
        );


        return pregunta;
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(delete, id);
    }

    @Override
    public List<PreguntaCerrada> saveList(List<PreguntaCerrada> preguntaCerradaList) {
        if(preguntaCerradaList.isEmpty()){
            return preguntaCerradaList;
        }
        PreguntaCerrada primeraPregunta = preguntaCerradaList.get(0);
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(consulta -> {
            PreparedStatement ps = consulta.prepareStatement(insertTablaPadre, new String[]{"id"});
            DAOutil.setPrepareStatement(ps, new Object[]{
                    primeraPregunta.getTipoPregunta(),
                    primeraPregunta.getEnunciado(),
                    primeraPregunta.getTema()
            });
            return ps;
        }, key);

        primeraPregunta.setId((Integer) key.getKey());

        for(PreguntaCerrada preguntaCerrada: preguntaCerradaList){
            KeyHolder key1 = new GeneratedKeyHolder();
            jdbcTemplate.update(consulta1 ->{
                PreparedStatement ps1 = consulta1.prepareStatement(insertHija, new String[]{"id_hija"});
                DAOutil.setPrepareStatement(ps1, new Object[]{
                        primeraPregunta.getId(),
                        preguntaCerrada.getOpcion(),
                        preguntaCerrada.isEs_correcta()
                });
                return ps1;
            }, key1);
        }
        return preguntaCerradaList;
    }
}
