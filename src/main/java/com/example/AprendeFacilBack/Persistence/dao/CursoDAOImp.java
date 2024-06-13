package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Persistence.mapper.CursoMapper;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import com.example.AprendeFacilBack.Web.util.DAOutil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Logger;


@Repository
public class CursoDAOImp implements CursoDAO{

    private final Logger log = Logger.getLogger(CursoDAOImp.class.getName());

    private static final String select = "SELECT curso.id, curso.nombre, " +
            "curso.descripcion, curso.id_asignatura, curso.calificacion, " +
            "asignatura.id as id_asignatura, asignatura.nombre as nombre_asignatura " +
            "FROM curso INNER JOIN asignatura ON curso.id_asignatura = asignatura.id";
    private static final String insert = "INSERT INTO curso (nombre, descripcion, id_asignatura, calificacion) VALUES (?,?,?,?)";
    private static final String update = "UPDATE curso SET nombre =?, descripcion =?, id_asignatira = ?, calificacion =? WHERE id = ?";
    private static final String delete = "DELETE curso WHERE id = ?";
    private static final String selectById = "select * from curso WHERE id = ?";

    private static final String selectAllName = "select nombre from curso";

    JdbcTemplate jdbcTemplate;

    public CursoDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Curso> getAll() {
        CursoMapper cursoMapper = new CursoMapper();
        cursoMapper.setQueryMetodoSelectALL(true);
        return jdbcTemplate.query(select, cursoMapper);
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

    @Override
    public List<String> getAllNamesFromCurso(){
        return jdbcTemplate.query(selectAllName, (rs, rowNum) -> rs.getString("nombre"));
    }

    @Override
    public Curso getCursoById(Integer id) throws AprendoFacilCustomException {
        Curso curso = null;
        try {
            curso =  jdbcTemplate.queryForObject(selectById, new go(), id);
        }catch (EmptyResultDataAccessException e){
            log.severe("Error: " + e.getMessage());
        }
        return curso;
    }
}
