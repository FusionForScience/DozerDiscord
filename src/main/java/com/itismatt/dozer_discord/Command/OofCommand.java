package com.itismatt.dozer_discord.Command;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.File;
import java.util.ArrayList;


/**
 * Command for "oof"
 */
public class OofCommand extends Command
{
    @SuppressWarnings("FieldCanBeLocal")
    private final String M_DEFAULT = "o\n o\n  f";              // Default output

    @SuppressWarnings("FieldCanBeLocal")
    private final int MAX_SIZE = 20;                            // Maximum size of output


    /**
     * Creates the oof command given a keyword for the command
     *
     * @param KEYWORD Keyword for the command
     */
    OofCommand(final String KEYWORD)
    {
        super(KEYWORD);
    }


    /**
     * Runs the Command
     *
     * @param msg MessageReceivedEvent used to grab properties from the sent message
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public void run(MessageReceivedEvent msg)
    {
        ArrayList<String> params;                           // List of parameters to grab off of commands


        if(triggered(msg))
        {
            MessageChannel channel = msg.getChannel();

            params = getParams(rawInput(msg));

            int size;                                       // Size of the "oof string" given by the first parameter

            // Display the oof image
            if(params.get(0).equals("img"))
            {
                ClassLoader loader = getClass().getClassLoader();

                File img = new File(loader.getResource("img/oof_img.png").getFile());
                channel.sendFile(img , null).queue();
            }

            else
            {
                try     // Sort of a hack- if the parameter can't be converted to an int it goes to the catch block
                {
                    size = Integer.valueOf(params.get(0));

                    if(size > MAX_SIZE)
                    {
                        channel.sendMessage("Can you not? Limit\'s 20").queue();
                    }
                    else if(size > 3)           // "oof" is three letters long
                    {
                        channel.sendMessage(m_getOofString(size)).queue();
                    }
                    else                        // Bad input
                    {
                        channel.sendMessage(M_DEFAULT).queue();
                    }
                }
                catch(Exception e)
                {
                    channel.sendMessage(M_DEFAULT).queue();
                }
            }
        }
    }


    /**
     * Forms the "oof string" based on the given size
     *
     * @param LENGTH Number of non-whitespace characters to make the "oof" String
     *
     * @return The completed "oof string"
     */
    private String m_getOofString(final int LENGTH)
    {
        StringBuilder strBuild = new StringBuilder();
        String indent = "";

        for(int i = 0; i < LENGTH - 1; i ++)
        {

            strBuild.append(indent);
            strBuild.append("o\n");

            indent += " ";
        }

        strBuild.append(indent);
        strBuild.append("f");

        return strBuild.toString();
    }
}
