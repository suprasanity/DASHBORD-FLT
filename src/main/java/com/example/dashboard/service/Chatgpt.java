package com.example.dashboard.service;

import org.springframework.stereotype.Component;
import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
@Component
public class Chatgpt {
    public final static String URL = " https://api.openai.com/v1/edits";
    public final String bearer="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";//your token here
    public final String model="text-davinci-edit-002";//your model here



    public String ask(String msg) throws IOException, UnirestException {

        String modelEngine = "text-davinci-002";
        HttpResponse<String> response = Unirest.post("https://api.openai.com/v1/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + bearer)
                .body("{\"model\": \"" + modelEngine + "\", \"prompt\": \"" + msg + "\", \"max_tokens\":1024, \"temperature\":0.5}")
                .asString();

        // Get the generated text
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response.getBody()).getAsJsonObject();
        JsonArray choices = json.getAsJsonArray("choices");
        JsonObject choice = choices.get(0).getAsJsonObject();
        String message = choice.get("text").getAsString();
        return message;
    }

}
