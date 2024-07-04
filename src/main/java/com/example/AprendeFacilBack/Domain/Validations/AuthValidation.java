package com.example.AprendeFacilBack.Domain.Validations;

import com.example.AprendeFacilBack.Domain.dto.UsuarioDTO;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import org.springframework.http.HttpStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthValidation {

    private static final Pattern UPPER_CASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWER_CASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z0-9]");
    private static final Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");


    public  boolean validatedEmail(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean validatePasword(String password){
        if (password == null || password.length() < 8) {
            return false;
        }
        if (!UPPER_CASE_PATTERN.matcher(password).find()) {
            return false;
        }
        if (!LOWER_CASE_PATTERN.matcher(password).find()) {
            return false;
        }
        if (!DIGIT_PATTERN.matcher(password).find()) {
            return false;
        }
        if (!SPECIAL_CHAR_PATTERN.matcher(password).find()) {
            return false;
        }
        return true;
    }

    public void ValidateRegisterUser(UsuarioDTO usuarioDTO) throws AprendoFacilCustomException {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (usuarioDTO.getId().isEmpty()){
            throw new AprendoFacilCustomException("Su id no debe estar vacio", httpStatus);
        }
        if (!validatePasword(usuarioDTO.getPassword())){
            throw new AprendoFacilCustomException("Contraseña invalida: " +
                    "Longitud mínima de 8 caracteres" + "\n" +
                    "Al menos una letra mayúscula" + "\n" +
                    "Al menos una letra minúscula" + "\n" +
                    "Al menos un dígito" + "\n" +
                    "Al menos un carácter especial. ", httpStatus);
        }

        if (usuarioDTO.getName().isEmpty() ){
            throw new AprendoFacilCustomException("Por favor ingrese un nombre", httpStatus);
        }
        if (usuarioDTO.getLastName().isEmpty()){
            throw new AprendoFacilCustomException("Por favor ingrese un apellido", httpStatus);
        }
        if (!validatedEmail(usuarioDTO.getEmail())){
            throw new AprendoFacilCustomException("El correo es no valido", httpStatus);
        }

        if(usuarioDTO.getPassword().isEmpty()){
            throw new AprendoFacilCustomException("La contraseña no es valida", httpStatus);
        }
        if (usuarioDTO.getPhone().isEmpty()){
            throw new AprendoFacilCustomException("Ingrese un telefono", httpStatus);

        }

    }
}
