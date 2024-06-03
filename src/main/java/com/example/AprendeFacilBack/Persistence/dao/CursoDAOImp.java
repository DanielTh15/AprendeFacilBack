package com.example.AprendeFacilBack.Persistence.dao;

import org.springframework.stereotype.Repository;
import org.springframework

@Repository
public class CursoDAOImp implements CursoDAO{

    private static final String select = "SELECT * FROM curso";

    JdbcTemplate jdbcTemplate;

}
