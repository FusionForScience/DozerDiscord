package com.itismatt.dozer_discord.Util.SpamCheck;


import net.dv8tion.jda.core.entities.User;


import java.util.HashMap;
import java.util.Map;


public class SpamCheck
{
    final static int SPAM_TIME = 30;        // If all messages are sent within this time in seconds, flag as spam

    final static int SPAM_AMOUNT = 15;      // Number of messages to send within time interval to count as spam
    final static int WARN_AMOUNT = 10;      // Number of messages to send within time interval to trigger a warning


    private Map<User, UserProfile> _userSpamCounter = new HashMap<>();


    public void check(User user)
    {
        if(_userSpamCounter.size() == 0 || _userSpamCounter.get(user) == null)
        {
            System.out.println("Adding new user: " + user.getName());

            _userSpamCounter.put(user , new UserProfile());
        }
        else
        {
            int spamValue;

            spamValue = _userSpamCounter.get(user).checkForSpam();


            switch(spamValue)
            {
                case 2:
                    System.out.println("Spam");
                    break;

                case 1:
                    System.out.println("Warning");
                    break;

                case 0:
                    System.out.println("No Spam");
            }
            System.out.println();
        }
    }
}
