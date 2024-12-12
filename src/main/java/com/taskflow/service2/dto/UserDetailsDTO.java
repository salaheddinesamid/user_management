package com.taskflow.service2.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class UserDetailsDTO {

    Integer userID;
    String firstName;
    String lastName;
    String email;
    String password;
    String role;

    public UserDetailsDTO(
            Integer userID,
            String firstName,
            String lastName,
            String email,
            String role
    ){
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
