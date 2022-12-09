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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@EnableScheduling

public class WebsiteApplication {
    public static String token ;
    public static String numChange ;

@Autowired
    Bot bot;
@Autowired
ServiceWSStatus serviceWSStatus;
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("HH.mm.ss");


    @Scheduled(fixedRate = 1000*60)
    public void run()  {
        HashMap<Long, WebService> map = new HashMap<>();
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
                if(!((WebService) list.get(i)).getStatus().equals("200"))
                {
                    bot.sendMsg("Le service "+((WebService) list.get(i)).getUrl()+" est KO son code erreur est " + ((WebService) list.get(i)).getStatus(),"779369141948776498","1049799594076291093");
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
