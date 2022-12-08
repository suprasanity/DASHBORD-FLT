package com.example.dashboard.DTO;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class Information {
    @JsonRawValue
    private String url;


    public Information(String url, String status) {
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

}
