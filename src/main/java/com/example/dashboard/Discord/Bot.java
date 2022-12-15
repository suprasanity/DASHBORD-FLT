package com.example.dashboard.Discord;


import com.example.dashboard.WebsiteApplication;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public  class Bot {
private JDA jda;
@Autowired
EventDisc eventDisc;


private  Logger logger = org.slf4j.LoggerFactory.getLogger(Bot.class);



@PostConstruct
public void init(){
    try{
        this.jda= JDABuilder.createDefault(WebsiteApplication.token).addEventListeners(eventDisc).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
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