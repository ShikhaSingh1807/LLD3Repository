package validations.gameValidation;

import exceptions.InvalidSymbolException;
import models.Player;
import models.Symbol;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueSymbolValidation
{
    public static void checkUniqueSymbolsForPlayers(List<Player> players)
    {
        Set<Character> setChar = new HashSet<>();

        for(Player player : players)
        {
            if(setChar.contains(player.getSymbol().getSym()))
                throw new InvalidSymbolException("Each Player must have unique symbol.");
            setChar.add(player.getSymbol().getSym());
        }
    }
}
