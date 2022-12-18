package com.example.dashboard.Discord;

import com.example.dashboard.WebsiteApplication;
import com.example.dashboard.service.Facturation;
import com.example.dashboard.service.Mail;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.utils.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class EventDisc implements EventListener {

    @Autowired
    Facturation facturation;

    @Autowired
    Mail mail;
    private final Logger logger = LoggerFactory.getLogger(EventDisc.class);


    public EventDisc() {
      this.logger.info("Bot discord Connected Event Ready");
    }


    @Override
    public void onEvent(GenericEvent event) {
        try {
            gestionMessage(event);
        } catch (IOException e) {
           logger.error(e.getMessage());
        }

    }

    private void gestionMessage(GenericEvent event) throws IOException {
        if (event instanceof MessageReceivedEvent) {
            MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
            if (messageReceivedEvent.getAuthor().isBot()) {
                return;
            }
            if (messageReceivedEvent.getMessage().getContentRaw().equals("!ping")) {
                messageReceivedEvent.getChannel().sendMessage("pong").queue();
            }
            if (messageReceivedEvent.getMessage().getContentRaw().startsWith("!facture")) {
                long rand= Math.round( Math.random() * ( 1000  ));
                facturation.build(messageReceivedEvent.getMessage().getContentRaw().split(" ")[1], messageReceivedEvent.getMessage().getContentRaw().split(" ")[2], Integer.parseInt( messageReceivedEvent.getMessage().getContentRaw().split(" ")[3]),rand);
                File f=new File(facturation.fileOutputPath+messageReceivedEvent.getMessage().getContentRaw().split(" ")[1]+rand+".pdf");
                FileUpload fileUpload = FileUpload.fromData(f);
                messageReceivedEvent.getChannel().sendMessage("Facture").addFiles(fileUpload).queue();
                mail.send(messageReceivedEvent.getMessage().getContentRaw().split(" ")[4],"test","test", f);
            }
            if (messageReceivedEvent.getMessage().getContentRaw().equals("!log")) {
               if (WebsiteApplication.log) {
                   WebsiteApplication.log = false;
                   messageReceivedEvent.getChannel().sendMessage("Log désactivé").queue();
                }
               else{
                   WebsiteApplication.log = true;
                   messageReceivedEvent.getChannel().sendMessage("Log activé").queue();
               }
            }
         }
    }

}
