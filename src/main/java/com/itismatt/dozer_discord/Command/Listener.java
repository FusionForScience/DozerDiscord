package com.itismatt.dozer_discord.Command;


import com.itismatt.dozer_discord.Util.SpamCheck.SpamCheck;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


/**
 * Class that listens for events that occur within the server
 */
public class Listener extends ListenerAdapter
{
    private SpamCheck _spamCheck = new SpamCheck();                 // Spam guard


    // Commands
    private PingCommand m_ping = new PingCommand("ping");

    private OofCommand m_oof = new OofCommand("oof");

    private SolveSudokuCommand m_sudoku = new SolveSudokuCommand("sudoku");


    /**
     * Currently checks message for commands and executes appropriately. Also checks for spam.
     *
     * @param event Event when a message is received
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        _spamCheck.check(event.getAuthor());

        m_ping.run(event);
        m_oof.run(event);
        m_sudoku.run(event);
    }
}
