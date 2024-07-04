package com.example.AprendeFacilBack.Persistence.dao;

import com.example.AprendeFacilBack.Domain.dto.Role;

public interface RoleDAO {
    boolean  updateRole(Role role);

    Role getRoleById(Integer idRole);
}
