package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Role;
import com.example.AprendeFacilBack.Persistence.mapper.RoleMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImp implements RoleDAO{

    private final JdbcTemplate jdbcTemplate;

    private static final String UPDATE_ROLE = "UPDATE ROLE SET idUser= ?, WHERE id = ?";

    private static final String FIND_ROLE_BY_ID = "SELECT * FROM ROLE WHERE ID = ?";

    public RoleDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean updateRole(Role role) {
        int updated = jdbcTemplate.update(UPDATE_ROLE,
                role.getIdUser(), role.getIdRole()
                );
        return updated > 0;
    }

    @Override
    public Role getRoleById(Integer idRole){
        Role role = null;
        try{
            role = jdbcTemplate.queryForObject(FIND_ROLE_BY_ID, new RoleMapper(), idRole);
        }catch (EmptyResultDataAccessException e){
        }
        return role;
    }
}
