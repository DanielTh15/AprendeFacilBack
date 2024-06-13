package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Tema;
import com.example.AprendeFacilBack.Persistence.mapper.TemaMapper;
import com.example.AprendeFacilBack.Web.util.DAOutil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TemaDAOImp implements TemaDAO{

    private static final String select = "SELECT * FROM tema";
    private static final String save = "INSERT INTO tema (id_curso, nombre, contenido, recursos) values (?,?,?,?)";


    JdbcTemplate jdbcTemplate;

    public TemaDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tema> getAll() {
        return jdbcTemplate.query(select, new TemaMapper());
    }

    @Override
    public List<Tema> lookByName(String name) {
        return null;
    }

    @Override
    public Tema save(Tema tema) {
        KeyHolder key = new GeneratedKeyHolder();
         jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(save, new String[]{"id"});
            DAOutil.setPrepareStatement(ps, new Object[]{
                    tema.getIdCurso(),
                    tema.getNombre(),
                    tema.getContenido(),
                    tema.getRecurso()
            });

            return ps;
        },key);
         //curso.setId(keyHolder.getKey().intValue());
        tema.setId(key.getKey().intValue());
        return tema;
    }

    @Override
    public void deleteBy(Integer id) {

    }

    @Override
    public Tema update(Tema tema) {
        return null;
    }
}
