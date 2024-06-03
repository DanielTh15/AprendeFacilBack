package com.example.AprendeFacilBack.Persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CursoDAOImp implements CursoDAO{

    private static final String select = "SELECT * FROM curso";

    JdbcTemplate jdbcTemplate;

}
