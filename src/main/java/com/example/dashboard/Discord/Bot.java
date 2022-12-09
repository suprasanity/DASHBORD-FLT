package com.example.dashboard.Discord;


import com.example.dashboard.WebsiteApplication;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
@Component
public  class Bot {
private JDA jda;
private  Logger logger = org.slf4j.LoggerFactory.getLogger(Bot.class);

public Bot() {
try{
    this.jda= JDABuilder.createDefault(WebsiteApplication.token).addEventListeners(new EventDisc()).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
    this.jda.awaitReady();
    this.sendMsg("Lancement du bot via la pipeline jenkins à chaque commit" ,"779369141948776498","1049799594076291093");
}catch (Exception ex){
   logger.error(ex.getMessage());
    this.sendMsg(ex.getMessage() ,"779369141948776498","1049799594076291093");
}
}

    public void sendMsg(String test ,String guildId,String channelId) {
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