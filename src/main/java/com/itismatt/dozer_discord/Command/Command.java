package com.itismatt.dozer_discord.Command;

import com.itismatt.dozer_discord.Debug;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;


/**
 * Base class for a command- all commands are assumed to be text-based and typed into a text channel where
 * the bot is active.
 */
public abstract class Command
{
    private String m_keyword;

    @SuppressWarnings("FieldCanBeLocal")
    private final String COMMAND_HEAD = "|";

    /**
     * Creates a command given a keyword- the keyword is the word that triggers the command
     *
     * @param KEYWORD A String that is the keyword used to trigger the command
     */
    Command(final String KEYWORD)
    {
        m_keyword = KEYWORD;
    }


    /**
     * Runs the command
     *
     * @param msg MessageReceivedEvent used to grab properties from the sent message
     */
    public abstract void run(MessageReceivedEvent msg);


    /**
     * Determines whether the command has been triggered or not, corresponding to the keyword
     *
     * @param msg Event that contains all properties of the sent message
     *
     * @return True if the command is triggered, false otherwise
     */
    @SuppressWarnings("WeakerAccess")
    protected final boolean triggered(MessageReceivedEvent msg)
    {
        String msgStr = rawInput(msg);

        int spaceIndex = msgStr.indexOf(" ");

        if(spaceIndex == -1)
        {
            return msgStr.equals(COMMAND_HEAD + m_keyword);
        }

        return msgStr.substring(0 , spaceIndex).equals(COMMAND_HEAD + m_keyword);
    }


    /**
     * Grabs the raw String from the message
     *
     * @param msg Event that contains all properties of the sent message
     *
     * @return The raw String of the message
     */
    @SuppressWarnings("WeakerAccess")
    protected final String rawInput(MessageReceivedEvent msg)
    {
        return msg.getMessage().getRawContent();
    }


    /**
     * Returns an ArrayList of parameters. A parameter is defined as anything after the keyword and separated by spaces.
     * All "words" are registered as parameters, which gives flexibility to how many parameters each command can take.
     *
     * @param fullCommand The raw String of the message
     *
     * @return An ArrayList of parameters occurring after the command. If no parameters are given, then the command is
     * returned (including the command header)
     */
    ArrayList<String> getParams(String fullCommand)
    {
        ArrayList<String> paramList = new ArrayList<String>();

        String params = fullCommand.substring(fullCommand.indexOf(" ") + 1);


        for(;;)
        {
            if(params.contains(" ") && params.length() > 0)
            {
                paramList.add(params.substring(0 , params.indexOf(" ")));

                params = params.substring(params.indexOf(" ") + 1);
            }
            else
            {
                paramList.add(params);
                break;
            }
        }


        if(Debug.print)
        {
            for (String aParamList : paramList)
            {
                System.out.println(aParamList);
            }
        }

        return paramList;
    }
}
