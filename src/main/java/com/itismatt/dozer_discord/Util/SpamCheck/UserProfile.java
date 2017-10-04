package com.itismatt.dozer_discord.Util.SpamCheck;


import java.util.ArrayList;


/**
 * A user profile for tracking spam- every user that types in chat for the first time has a profile created for them
 */
class UserProfile
{
    private ArrayList<Integer> _messageTimes = new ArrayList<>();            // Stores time between each message sent

    private int _lastTimeSent = (int)(System.currentTimeMillis() / 1000);     // The last time the message was sent


    /**
     * Creates a user profile
     */
    UserProfile()
    {
        // Nothing
    }


    /**
     * Records the message times in an array list- only stores SPAM_AMOUNT in SpamCheck
     */
    private void _recordMsgTime()
    {

        _messageTimes.add((int)(System.currentTimeMillis() / 1_000) - _lastTimeSent);

        System.out.println("Adding: " + ((int)(System.currentTimeMillis() / 1_000) - _lastTimeSent));

        if(_messageTimes.size() == SpamCheck.SPAM_AMOUNT + 1)
        {
            _messageTimes.remove(0);
        }

        System.out.println("Size: " + _messageTimes.size());

        System.out.println("Last time sent: " + _lastTimeSent);

        _lastTimeSent = (int)(System.currentTimeMillis() / 1_000);

        System.out.println("This send time: " + _lastTimeSent);
    }


    /**
     * Checks for spam, should be called every time a message is sent
     *
     * @return 2 if spam is detected, 1 if a warning should be issued, 0 if no spam
     */
    int checkForSpam()
    {
        _recordMsgTime();

        int totalTime = 0;

        for(int i = _messageTimes.size() - 1 , j = 0; i >= 0; i -- , j++)
        {
            totalTime += _messageTimes.get(i);

            if(totalTime <= SpamCheck.SPAM_TIME)
            {
                if(j + 1 >= SpamCheck.SPAM_AMOUNT)
                {
                    System.out.println("total time: " + totalTime);

                    return 2;
                }
                else if(j + 1 >= SpamCheck.WARN_AMOUNT && i == 0)
                {
                    System.out.println("total time: " + totalTime);

                    return 1;
                }
            }
        }

        System.out.println("total time: " + totalTime);

        return 0;
    }
}
