package validations.gameValidation;

import exceptions.MaximumPlayerInGameException;
import exceptions.MinimumPlayerInGameException;
import models.Player;

import java.util.List;

public class NumberOfPlayerValidation
{
    public static void checkNumberOfPlayersInGame(List<Player> players,int dimension)
    {
        if (players == null)
            throw new MinimumPlayerInGameException("Players cannot be null.");
        if(players.size()<2)
            throw  new MinimumPlayerInGameException("Game should have at least 2 players.");
        if(players.size()>dimension-1)
            throw new MaximumPlayerInGameException("Game should not have more than N-1 players!!!");
    }
}
