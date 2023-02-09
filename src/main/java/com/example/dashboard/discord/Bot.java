package com.example.dashboard.discord;



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
public void init() throws InterruptedException {

        this.jda = JDABuilder.createDefault(WebsiteApplication.token).addEventListeners(eventDisc).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
        this.jda.awaitReady();
        logger.info("Bot discord Connected");
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