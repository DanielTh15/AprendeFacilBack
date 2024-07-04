package com.example.AprendeFacilBack.Persistence.mapper;

import com.example.AprendeFacilBack.Domain.dto.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setIdRole(Integer.parseInt(resultSet.getString("id")));
        role.setName(resultSet.getString("name"));
        role.setIdUser(resultSet.getString("idUser"));
        return role;
    }
}
