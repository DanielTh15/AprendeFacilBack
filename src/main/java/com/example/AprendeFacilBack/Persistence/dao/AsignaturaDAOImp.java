package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Asignatura;
import com.example.AprendeFacilBack.Persistence.mapper.AsignaturaMapper;
import com.example.AprendeFacilBack.Web.util.DAOutil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class AsignaturaDAOImp implements AsignaturaDAO{

    private static final String select = "SELECT * FROM asignatura";
    private static final String selectById = select + " WHERE id = ?";
    private static final String insert = "INSERT INTO asignatura (nombre) VALUES (?)";
    private static final String delete = "DELETE FROM asignatura WHERE id = ?";

    JdbcTemplate jdbcTemplate;

    public AsignaturaDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Asignatura> getAll() {
        return jdbcTemplate.query(select, new AsignaturaMapper());
    }

    @Override
    public Asignatura save(Asignatura asignatura) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insert, new String[]{"id"});
            DAOutil.setPrepareStatement(ps, new Object[]{
                    asignatura.getNombre()
            });
            return ps;
        }, keyHolder);
        asignatura.setId(keyHolder.getKey().intValue());
        return asignatura;
    }

    @Override
    public Asignatura findById(Integer id) {
        return jdbcTemplate.queryForObject(selectById, new AsignaturaMapper(), id);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Asignatura update(Asignatura asignatura) {
        return null;
    }
}
