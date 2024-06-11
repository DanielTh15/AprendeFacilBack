package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Pregunta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PreguntaMapper<T extends Pregunta> implements RowMapper<T> {

    /* Usamos generics que es para usar una subclase de "Pregunta" asegurándose de usar todos los atributos y métodos
    de la clase principal */
    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        T pregunta = createPreguntaInstance();
         pregunta.setId(rs.getInt("id"));
         pregunta.setTipoPregunta(rs.getString("tipo_pregunta"));
         pregunta.setEnunciado(rs.getString("enunciado"));
         pregunta.setTema(rs.getInt("id_tema"));
         mapSpecificFields(rs, pregunta);
        return pregunta;
    }

    protected abstract T createPreguntaInstance() throws SQLException;

    protected abstract void mapSpecificFields(ResultSet rs, T pregunta) throws SQLException;

}
