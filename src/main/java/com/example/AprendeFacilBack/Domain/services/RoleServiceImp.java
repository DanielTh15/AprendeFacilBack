package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Role;
import com.example.AprendeFacilBack.Persistence.dao.RoleDAO;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImp(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleById(Integer idRole){
        return roleDAO.getRoleById(idRole);
    }

    @Override
    public boolean updateRole(Role role) throws AprendoFacilCustomException {
        boolean roleUpdated = roleDAO.updateRole(role);
        if (!roleUpdated){
            throw new AprendoFacilCustomException("This role can´t updated", HttpStatus.NOT_FOUND);
        }
        return roleUpdated;
    }

}
