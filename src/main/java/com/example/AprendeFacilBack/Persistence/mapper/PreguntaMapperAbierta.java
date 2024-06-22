package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Pregunta;
import com.example.AprendeFacilBack.Domain.dto.PreguntaAbierta;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PreguntaMapperAbierta extends PreguntaMapper<PreguntaAbierta>{
    @Override
    protected PreguntaAbierta createPreguntaInstance() throws SQLException {
        return new PreguntaAbierta();
    }

    @Override
    protected void mapSpecificFields(ResultSet rs, PreguntaAbierta preguntaAbierta) throws SQLException {
        preguntaAbierta.setId_tabla_hija(rs.getInt("id_hija"));
        preguntaAbierta.setId_pregunta(rs.getInt("id_pregunta"));
        preguntaAbierta.setRespuesta_correcta(rs.getString("respuesta_correcta"));

    }
}
