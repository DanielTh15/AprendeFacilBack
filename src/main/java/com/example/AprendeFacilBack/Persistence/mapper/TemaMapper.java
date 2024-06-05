package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Tema;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemaMapper implements RowMapper<Tema> {
    @Override
    public Tema mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
