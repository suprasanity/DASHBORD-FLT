package com.example.dashboard.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
@Component
public class Chatgpt {
    public static final  String URL = "https://api.openai.com/v1/completions";
    public static final String bearer="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";//your token here
    public static final String model="text-davinci-edit-001";//your model here



    public String ask(String msg) throws IOException {

        URL url = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + bearer);
        con.addRequestProperty("model", model);
        con.addRequestProperty("input", msg);
        con.addRequestProperty("instruction", "chat");
        con.setRequestMethod("POST");
        return con.getResponseMessage();
    }

}
