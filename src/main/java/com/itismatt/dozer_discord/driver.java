package com.itismatt.dozer_discord;


import com.itismatt.dozer_discord.Command.Listener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;


/**
 * Driver class- contains main.
 */
public class driver
{
    @SuppressWarnings("FieldCanBeLocal")
    private static String m_token = Token.get();

    private static JDA m_jda;


    /**
     * Sets up JDA bot and runs it
     *
     * @param args Arguments given through the ide (or maybe a command line)
     *
     * @throws Exception Some runtime exception
     */
    public static void main(String[] args) throws Exception
    {
        try
        {
            m_jda = new JDABuilder(AccountType.BOT).setToken(m_token).buildBlocking();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        m_jda.addEventListener(new Listener());
        m_jda.getPresence().setGame(Game.of("Matt\'s fire playlist"));          // Sets the "is playing: " blurb
    }
}
