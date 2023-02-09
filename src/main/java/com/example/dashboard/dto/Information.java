package com.example.dashboard.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonRawValue;

public class Information {
    @JsonRawValue
    private String url;

    @JsonCreator
    public Information(String url) {
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

}
