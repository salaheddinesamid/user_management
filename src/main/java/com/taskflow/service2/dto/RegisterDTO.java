package com.taskflow.service2.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public RegisterDTO(
            String firstName,
            String lastName,
            String email,
            String password,
            String role
    ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
