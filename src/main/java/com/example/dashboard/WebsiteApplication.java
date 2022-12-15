package com.example.dashboard;
import com.example.dashboard.Discord.Bot;
import com.example.dashboard.Discord.EventDisc;
import com.example.dashboard.model.WebService;
import com.example.dashboard.service.ServiceWSStatus;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@SpringBootApplication
@EnableScheduling

public class WebsiteApplication {

    public static boolean log = false;

    public static String token ;
    public static String numChange = null;

@Autowired
    Bot bot;
@Autowired
ServiceWSStatus serviceWSStatus;

@Autowired
EventDisc eventDisc;


    @Scheduled(fixedRate = 1000*60)
    public void run()  {
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
                    bot.sendMsg("Le service "+( list.get(i)).getUrl()+" est KO son code erreur est " + ( list.get(i)).getStatus(),"812813246499127296","1045799117374365716");
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
