package com.example.dashboard.Discord;

import com.example.dashboard.WebsiteApplication;
import com.example.dashboard.utils.Facturation;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.utils.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Component
public class EventDisc implements EventListener {

    private final Logger logger = LoggerFactory.getLogger(EventDisc.class);


    public EventDisc() {
      this.logger.info("Bot discord Connected Event Ready");
    }


    @Override
    public void onEvent(GenericEvent event) {
        try {
            gestionMessage(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
                Facturation facturation = new Facturation(messageReceivedEvent.getMessage().getContentRaw().split(" ")[1]);
                FileUpload fileUpload = FileUpload.fromData(new File(facturation.fileOutputPath+messageReceivedEvent.getMessage().getContentRaw().split(" ")[1]+".pdf"));
                messageReceivedEvent.getChannel().sendMessage("Facture").addFiles(fileUpload).queue();
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
