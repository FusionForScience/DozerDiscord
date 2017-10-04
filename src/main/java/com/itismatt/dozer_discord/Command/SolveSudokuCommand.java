package com.itismatt.dozer_discord.Command;

import com.itismatt.dozer_discord.Util.Sudoku;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;


public class SolveSudokuCommand extends Command
{
    SolveSudokuCommand(final String KEYWORD)
    {
        super(KEYWORD);
    }


    public void run(MessageReceivedEvent msg)
    {
        ArrayList<String> params;


        if(triggered(msg))
        {
            MessageChannel channel = msg.getChannel();

            params = getParams(rawInput(msg));

            try
            {
                Sudoku sudoku = new Sudoku();

                sudoku.getPuzzle(params.get(0));

                System.out.println("Solving");
                sudoku.solvePuzzle();

                sudoku.printPuzzle();

                channel.sendMessage(sudoku.puzzleString()).queue();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
