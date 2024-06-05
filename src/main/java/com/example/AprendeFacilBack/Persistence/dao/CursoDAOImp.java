package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Persistence.mapper.CursoMapper;
import com.example.AprendeFacilBack.Web.util.DAOutil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;


@Repository
public class CursoDAOImp implements CursoDAO{

    private static final String select = "SELECT * FROM curso";
    private static final String insert = "INSERT INTO curso (nombre, descripcion, id_asignatura, calificacion) VALUES (?,?,?,?)";
    private static final String update = "UPDATE curso SET nombre =?, descripcion =?, id_asignatira = ?, calificacion =? WHERE id = ?";
    private static final String delete = "DELETE curso WHERE id = ?";
    private static final String selectById = select + "WHERE id = ?";

    JdbcTemplate jdbcTemplate;

    public CursoDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Curso> getAll() {
        return jdbcTemplate.query(select, new CursoMapper());
    }

    @Override
    public Curso save(Curso curso) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            // el primer argumento en prepareStatement inserta los datos y el segundo trae la clave autogenerada
            PreparedStatement ps = con.prepareStatement(insert, new String[]{"id"});
            DAOutil.setPrepareStatement(ps, new Object[]{
                 curso.getName(),
                 curso.getDecription(),
                 curso.getId_asignatura(),
                 curso.getCalificacion()
            });
            return ps;
        }, keyHolder);
        curso.setId(keyHolder.getKey().intValue());
        return curso;
    }
}
