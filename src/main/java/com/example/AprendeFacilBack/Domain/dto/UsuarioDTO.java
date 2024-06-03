package com.example.AprendeFacilBack.Domain.dto;

import lombok.Data;

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

}
