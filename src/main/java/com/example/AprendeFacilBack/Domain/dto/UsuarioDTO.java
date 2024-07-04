package com.example.AprendeFacilBack.Domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class UsuarioDTO {
    private String id;
    private String name;
    private String lastName;
    private Boolean locked;
    private Boolean disable;
    private String email;
    private String phone;
    private String password;
    private String rol;

}
