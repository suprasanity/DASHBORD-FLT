package com.example.dashboard.Controller;

import com.example.dashboard.DTO.Information;
import com.example.dashboard.Discord.Bot;
import com.example.dashboard.model.WebService;
import com.example.dashboard.service.ServiceWSStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "chovy.freeboxos.fr")
public class ControllerFront {

    Process p;

   private Logger logger =org.slf4j.LoggerFactory.getLogger(ControllerFront.class);
    @Autowired
    Bot bot;

    @Autowired
    ServiceWSStatus serviceWSStatus;



    @PostMapping("/botMsg")
    @ResponseBody
    public String sendMsg(@RequestBody String msg){
        bot.sendMsg(msg,"812813246499127296","1045799117374365716");
        return "true";
    }

    @PostMapping("/saveService")
    @ResponseBody
    public String saveService(@RequestBody Information url){
        serviceWSStatus.addWS(url.getUrl());
        return "true";
    }

    @GetMapping("/getAllService")
    @ResponseBody
    public List <WebService> getAllService(){
        return serviceWSStatus.getAllWS();
    }
    @PostMapping("/deleteService")
    @ResponseBody
    public String deleteService(@RequestBody String id){
        serviceWSStatus.deleteWS(Long.parseLong(id.substring(3)));
        return "true";
    }

   @PostMapping("/getStatus")
    @ResponseBody
    public String getStatus(@RequestBody String id){
        return serviceWSStatus.getStatus(Long.parseLong(id.substring(3)));
    }
    @GetMapping("StartMc")
    @ResponseBody
    public String startMc() {
        try {
            //todo remove deprecated
            p = Runtime.getRuntime().exec("java -jar D:\\workspace\\dashboard\\dashboard\\target\\website-0.0.1-SNAPSHOT.jar");

        }catch (IOException e){
            this.logger.error(e.getMessage());
            return "Une erreur à été rencontrer durant le démarage du serveur. : "+e.getMessage();
        }
        return "true";
    }
    @GetMapping("StopMc")
    @ResponseBody
    public String stopMc() {
        try {
            p.destroy();
        }catch (Exception e){
            this.logger.error(e.getMessage());
            return "Une erreur à été rencontrer durant le démarage du serveur. : "+e.getMessage();
        }

        return "true";
    }
    @PostMapping("/getWS")
    @ResponseBody
    public WebService getWS(@RequestBody String id){
        return serviceWSStatus.getWS(Long.parseLong(id.substring(3)));
    }

}
