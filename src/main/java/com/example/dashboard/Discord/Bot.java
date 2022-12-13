package com.example.dashboard.Discord;


import com.example.dashboard.WebsiteApplication;
import com.example.dashboard.utils.Facturation;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileUpload;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public  class Bot {
private JDA jda;
private  Logger logger = org.slf4j.LoggerFactory.getLogger(Bot.class);

public Bot() {
try{
    this.jda= JDABuilder.createDefault(WebsiteApplication.token).addEventListeners(new EventDisc()).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
    this.jda.awaitReady();
}catch (Exception ex){
   logger.error(ex.getMessage());
    this.sendMsg(ex.getMessage() ,"812813246499127296","1045799117374365716");
}
}

    public void sendMsg(String test , String guildId, String channelId) {
    try{
        TextChannel textChannel = this.jda.getGuildById(guildId).getTextChannelById(channelId);
        if(textChannel.canTalk()) {

                textChannel.sendMessage(test).queue();
        }
    }catch (  Exception ex){
        logger.error(ex.getMessage());
    }
    }

}