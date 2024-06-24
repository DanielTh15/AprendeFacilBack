package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.PreguntaCerrada;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PreguntaMapperCerrada extends PreguntaMapper<PreguntaCerrada>{

    @Override
    protected PreguntaCerrada createPreguntaInstance() throws SQLException {
        return new PreguntaCerrada();
    }

    @Override
    protected void mapSpecificFields(ResultSet rs, PreguntaCerrada pregunta) throws SQLException {
        pregunta.setIdHija(rs.getInt("id_hija"));
        pregunta.setId_pregunta(rs.getInt("id_pregunta"));
        pregunta.setOpcion(rs.getString("opcion"));
        pregunta.setEs_correcta(rs.getBoolean("es_correcta"));
    }
}
