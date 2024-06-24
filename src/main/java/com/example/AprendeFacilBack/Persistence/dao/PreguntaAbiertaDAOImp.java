package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Pregunta;
import com.example.AprendeFacilBack.Domain.dto.PreguntaAbierta;
import com.example.AprendeFacilBack.Persistence.mapper.PreguntaMapperAbierta;
import com.example.AprendeFacilBack.Web.util.DAOutil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PreguntaAbiertaDAOImp extends PreguntaDAO<PreguntaAbierta> implements Pregunta_AbiertaDAO{

    private static final String insertTablaPadre = "INSERT INTO pregunta (tipo_pregunta, enunciado, id_tema) VALUES (?,?,?)";
    private static final String insertTablaHija = "INSERT INTO pre_abierta (id_pregunta, respuesta_correcta) VALUES (?,?)";
    private static final String select = "SELECT * FROM pregunta p INNER JOIN pre_abierta pa ON p.id = pa.id_pregunta";
    private static final String selectById = select + "WHERE p.id = ?";
    private static final String updateTablaPadre = "UPDATE pregunta SET tipo_pregunta = ?, enunciado = ?, id_tema = ? WHERE id = ?";
    private static final String updateTablaHija = "UPDATE pre_abierta SET respuesta_correcta = ? WHERE id_pregunta = ?";
    private static final String delete = "DELETE FROM pregunta p WHERE p.id = ?";

    public PreguntaAbiertaDAOImp(JdbcTemplate jdbcTemplate) {

        super(jdbcTemplate);
    }


    @Override
    public List<PreguntaAbierta> list() {
        return jdbcTemplate.query(select, new PreguntaMapperAbierta());
    }

    @Override
    public PreguntaAbierta getById(Integer id) {
        return jdbcTemplate.queryForObject(selectById, new PreguntaMapperAbierta(), id);
    }

    @Override
    public PreguntaAbierta save(PreguntaAbierta pregunta) {
        KeyHolder key = new GeneratedKeyHolder();
        KeyHolder key2 = new GeneratedKeyHolder();
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

        jdbcTemplate.update(consulta1 ->{
            PreparedStatement ps = consulta1.prepareStatement(insertTablaHija, new String[]{"id_hija"});
            DAOutil.setPrepareStatement(ps, new Object[]{
                    pregunta.getId(),
                    pregunta.getRespuesta_correcta()
            });
            return ps;
        }, key2);

        return pregunta;
    }

    @Override
    public PreguntaAbierta update(PreguntaAbierta pregunta) {

     /*   System.out.println(pregunta.getId()+ "\n" + pregunta.getTipoPregunta() + "\n" +pregunta.getEnunciado()+ "\n" + pregunta.getTema() + "\n" +
        pregunta.getId_tabla_hija() +"\n"+ pregunta.getId_pregunta() +"\n"+ pregunta.getRespuesta_correcta()
        );
     */
       jdbcTemplate.update(updateTablaPadre,
               pregunta.getTipoPregunta(),
               pregunta.getEnunciado(),
               pregunta.getTema(),
               pregunta.getId()
       );

       jdbcTemplate.update(updateTablaHija,
               pregunta.getRespuesta_correcta(),
               pregunta.getId()
       );

        return pregunta;
    }

    @Override
    public void delete(Integer id) {

        jdbcTemplate.update(delete, id);
    }
}
