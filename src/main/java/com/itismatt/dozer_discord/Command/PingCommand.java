package com.itismatt.dozer_discord.Command;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


/**
 * Simple feedback command
 */
public class PingCommand extends Command
{
    /**
     * Creates a "ping" command
     *
     * @param KEYWORD Keyword for the command
     */
    PingCommand(final String KEYWORD)
    {
        super(KEYWORD);
    }


    /**
     * Runs the "ping" command- serves as a sort of feedback. Bot replies with "pong!"
     *
     * @param msg MessageReceivedEvent used to grab properties from the sent message
     */
    @Override
    public void run(MessageReceivedEvent msg)
    {
        if(triggered(msg))
        {
            MessageChannel channel = msg.getChannel();

            channel.sendMessage("Pong!").queue();
        }
    }
}
