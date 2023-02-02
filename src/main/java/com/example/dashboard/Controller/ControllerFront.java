package com.example.dashboard.Controller;

import com.example.dashboard.DTO.DTOUser;
import com.example.dashboard.DTO.Information;
import com.example.dashboard.Discord.Bot;
import com.example.dashboard.model.WebService;
import com.example.dashboard.service.ServiceUser;
import com.example.dashboard.service.ServiceWSStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.io.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://chovy.freeboxos.fr/signIn")
@RequestMapping("/api")
public class ControllerFront {

    Process p;
    @Autowired
    ServiceUser serviceUser;

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
    @GetMapping("/StartMc")
    @ResponseBody
    public String startMc() throws InterruptedException, IOException {

            ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Java\\jdk-1.8.0_202\\bin\\java" ,"-server" ,"-Xms10000m", "-Xmx10000m","-XX:+UseG1GC", "-Dsun.rmi.dgc.server.gcInterval=2147483646", "-XX:+UnlockExperimentalVMOptions", "-XX:G1NewSizePercent=20", "-XX:G1ReservePercent=20" ,"-XX:MaxGCPauseMillis=50", "-XX:G1HeapRegionSize=32M", "-Dfml.readTimeout=180", "-jar","forge-1.12.2-14.23.5.2860.jar", "nogui");
            pb.directory(new File("C:\\Users\\yann\\Desktop\\mc\\mcMod"));
            pb.environment();
            logger.info("Starting minecraft");
            pb.inheritIO();
            p= pb.start();
            p.waitFor();
            logger.info("Minecraft started");
        return "true";
    }
    @GetMapping("/StopMc")
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


    @PostMapping("/login")
    @ResponseBody
    public boolean isLogged(@RequestBody DTOUser user) {

        this.logger.info(serviceUser.isLogged(user.getLogin(),user.getPassword()).toString());
        return serviceUser.isLogged(user.getLogin(),user.getPassword());
    }

    @PostMapping("/register")
    @ResponseBody
    public boolean register(@RequestBody String login , @RequestBody String password, @RequestBody String email, @RequestBody String tel) {
        return serviceUser.register(login,password,email,tel);
    }

    @PostMapping("/getWSStatudByName")
    @ResponseBody
    public String getWSStatudByName(@RequestBody String name){
        return serviceWSStatus.getWSStatudByName(name);
    }

}
