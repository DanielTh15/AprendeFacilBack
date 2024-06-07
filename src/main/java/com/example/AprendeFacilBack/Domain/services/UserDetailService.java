package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Persistence.dao.UsuarioDAO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private UsuarioDAO usuarioDAO;

    public UserDetailService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String emailUser) throws UsernameNotFoundException {
        UsuarioDTO usuarioDTO = usuarioDAO.getUserByEmail(emailUser);
        if (usuarioDTO == null) {
            throw new UsernameNotFoundException("User not found with email: " + emailUser);
        }

        return User.builder()
                .username(usuarioDTO.getEmail())
                .password(usuarioDTO.getPassword())
                .accountLocked(usuarioDTO.getLocked())
                .disabled(usuarioDTO.getDisable())
                .build();
    }

}
