package com.itismatt.dozer_discord.Command;


import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class WhatDidKathleenSayCommand extends Command implements Runnable
{
    private boolean _running = false;
    private MessageReceivedEvent _lastMsg;
    private Thread _t;


    /**
     * Creates a "what the heck did Kathleen say because she keeps deleting her messages" command
     *
     * @param KEYWORD Keyword for the command
     */
    WhatDidKathleenSayCommand(final String KEYWORD)
    {
        super(KEYWORD);
    }


    /**
     * Runs the command- it's a bot that repeats what Kathleen says so the WE CAN SEE IT BEFORE IT GETS DELETED
     *
     * Actually, what the function does is start the thread that looks for messages Kathleen sent- it doesn't do
     * anything else
     *
     * @param msg MessageReceivedEvent used to grab properties from the sent message
     */
    @Override
    public void run(MessageReceivedEvent msg)
    {
        _lastMsg = msg;

        if(triggered(msg))
        {
            _running = !_running;

            if(_running)
            {
                MessageChannel channel = msg.getChannel();

                channel.sendMessage("Now listening for Kathleen!").queue();

                _t = new Thread();
                _t.start();
            }
            else
            {
                MessageChannel channel = msg.getChannel();

                channel.sendMessage("No longer listening. Bye!").queue();
            }
        }
    }


    /**
     * Runnable's run(), because this command needs to run on a separate thread. Confusing!
     *
     * This is function that actually repeats back what Kathleen said
     */
    @Override
    public void run()
    {
        // Mine: FusionForScience#2011
        // Kathleen's: beetnerk#7549

        boolean copySent = false;

        while(_running)
        {
            if(_lastMsg.getAuthor().getDiscriminator().equals("FusionForScience#2011") && !copySent)
            {
                MessageChannel channel = _lastMsg.getChannel();

                channel.sendMessage(_lastMsg.getMessage()).queue();

                copySent = true;
            }
        }
    }
}
