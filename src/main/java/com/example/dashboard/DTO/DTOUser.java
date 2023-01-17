package com.example.dashboard.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonRawValue;

public class DTOUser {


    @JsonRawValue
    private String login;
    @JsonRawValue
    private String password;

    @JsonCreator
    public DTOUser(String username, String password) {
        this.login = username;
        this.password = password;
    }
   public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

}