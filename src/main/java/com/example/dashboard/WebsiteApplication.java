package com.example.dashboard;

import com.example.dashboard.Discord.Bot;
import com.example.dashboard.Discord.EventDisc;
import com.example.dashboard.model.WebService;
import com.example.dashboard.service.Mail;
import com.example.dashboard.service.ServiceWSStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
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

    public static boolean log = false;

    public static String token = null;
    public static String numChange = null;


@Autowired
    Bot bot;
@Autowired
ServiceWSStatus serviceWSStatus;
private Logger logger = LoggerFactory.getLogger(this.getClass());
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
                if(!( list.get(i)).getStatus().equals("200") && log)
                {
                    bot.sendMsg("Le service "+( list.get(i)).getUrl()+" est KO son code erreur est " + ( list.get(i)).getStatus(),"779369141948776498","1049799594076291093");
                }
          }

    }




    public static void main(String[] args) {
        token = args[0];
        if(args.length>1)
        {
             numChange = args[1];
        }
        SpringApplication.run(WebsiteApplication.class, args);
    }
}
