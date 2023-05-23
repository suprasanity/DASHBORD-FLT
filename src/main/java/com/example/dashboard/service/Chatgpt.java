package com.example.dashboard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
@Component
public class Chatgpt {
    public static final  String URL = "https://api.openai.com/v1/completions";

    @Value("${openApi.token}")
    public String BEARER;
    //your token here
    public static final String MODEL ="gpt-3.5-turbo";//your model here



    public String ask(String msg) throws IOException {

        URL url = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + BEARER);
        con.addRequestProperty("model", MODEL);
        con.addRequestProperty("input", msg);
        con.addRequestProperty("instruction", "chat");
        con.setRequestMethod("POST");
        return con.getResponseMessage();
    }

}
