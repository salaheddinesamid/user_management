package com.taskflow.service2.dto;

import com.taskflow.service2.model.User;
import lombok.Data;

@Data
public class BearerToken {
    private String accessToken ;
    private String tokenType ;
    private User user;
    public BearerToken(String accessToken , String tokenType, User user) {
        this.tokenType = tokenType ;
        this.accessToken = accessToken;
        this.user = user;
    }
}
