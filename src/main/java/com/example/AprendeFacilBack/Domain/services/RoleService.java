package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Role;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;

public interface RoleService {
    Role getRoleById(Integer idRole);

    boolean updateRole(Role role) throws AprendoFacilCustomException;
}
