package com.example.dashboard.Discord;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;




public class EventDisc implements EventListener {

    private Logger logger = LoggerFactory.getLogger(EventDisc.class);

    private boolean bonALlowed = true;
    private int count = 0;
    @Autowired
    Bot bot;
    public EventDisc() {
      this.logger.info("Bot discord Connected Event Ready");
    }


    @Override
    public void onEvent(GenericEvent event) {
        gestionMessage(event);

    }

    private void gestionMessage(GenericEvent event) {
        if (event instanceof MessageReceivedEvent) {
            MessageReceivedEvent messageReceivedEvent = (MessageReceivedEvent) event;
            if (messageReceivedEvent.getAuthor().isBot()) {
                return;
            }
            if (messageReceivedEvent.getMessage().getContentRaw().equals("!ping")) {
                messageReceivedEvent.getChannel().sendMessage("pong").queue();
            }
        }
    }

}

