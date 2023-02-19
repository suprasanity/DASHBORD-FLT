package com.example.dashboard;

import com.example.dashboard.discord.Bot;
import com.example.dashboard.discord.EventDisc;
import com.example.dashboard.model.WebService;
import com.example.dashboard.service.Mail;
import com.example.dashboard.service.ServiceWSStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@SpringBootApplication
@EnableScheduling

public class WebsiteApplication  extends SpringBootServletInitializer {


@Autowired
    Bot bot;
@Autowired
ServiceWSStatus serviceWSStatus;
@Autowired
EventDisc eventDisc;

@Autowired
Mail mail;

    @Scheduled(fixedRate = 1000*60)
    public void run() throws IOException {

        List <WebService>list = serviceWSStatus.getAllWS();
         for (int i = 0; i < list.size(); i++) {
             try{
                 URL url = new URL( (list.get(i)).getUrl());
                 HttpURLConnection con = (HttpURLConnection) url.openConnection();
                 con.setRequestMethod("GET");
                 con.connect();
                 serviceWSStatus.updateStatus((list.get(i)).getId(),String.valueOf(con.getResponseCode()));
             }catch (Exception e){
                 serviceWSStatus.updateStatus((list.get(i)).getId(),"404");
             }
          }

    }




    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }
}
