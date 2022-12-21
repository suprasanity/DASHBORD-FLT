package com.example.dashboard.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
@Component
public class Chatgpt {
    public final static String URL = "https://chatgpt.herokuapp.com/api/chat";
    public final String bearer="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";//your token here
    public final String model="davinci";//your model here



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
