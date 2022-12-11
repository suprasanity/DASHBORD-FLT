package com.example.dashboard;
import com.example.dashboard.Discord.Bot;
import com.example.dashboard.model.WebService;
import com.example.dashboard.service.ServiceWSStatus;
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
    public static String numChange ;

@Autowired
    Bot bot;
@Autowired
ServiceWSStatus serviceWSStatus;


    @Scheduled(fixedRate = 1000*60)
    public void run()  {
        List list = serviceWSStatus.getAllWS();
         for (int i = 0; i < list.size(); i++) {
             try{
                 URL url = new URL(((WebService) list.get(i)).getUrl());
                 HttpURLConnection con = (HttpURLConnection) url.openConnection();
                 con.setRequestMethod("GET");
                 con.connect();
                 serviceWSStatus.updateStatus(((WebService) list.get(i)).getId(),String.valueOf(con.getResponseCode()));
             }catch (Exception e){
                 serviceWSStatus.updateStatus(((WebService) list.get(i)).getId(),"404");
             }
                if(!((WebService) list.get(i)).getStatus().equals("200") && log)
                {
                    bot.sendMsg("Le service "+((WebService) list.get(i)).getUrl()+" est KO son code erreur est " + ((WebService) list.get(i)).getStatus(),"812813246499127296","1045799117374365716");
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
