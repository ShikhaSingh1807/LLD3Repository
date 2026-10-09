package validations.gameValidation;

import exceptions.InvalidBotCountException;
import models.Player;
import models.enums.PlayerType;

import java.util.List;

public class NumberOfBotValidation
{
    public static void checkNumberOfBot(List<Player> players)
    {
        int numberOfBot = 0;

        for(Player player:players)
        {
            if(player.getPlayerType()== PlayerType.BOT)
                numberOfBot++;
        }

        if(numberOfBot>1)
            throw  new InvalidBotCountException("Bot count should be 0 or 1 .");

    }
}
