package strategies.botplayingstrategy;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;
import models.enums.CellState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MediumBotPlayingStrategy implements BotPlayingStrategy
{
    //Medium Algo can be  randomly selecting an empty cell
    //Way 1 :- Randomly generate row and column (from 0 to 2) and check if empty return it else continue
    //TC :- Expected time increases as the board becomes full, but there is no deterministic O(N² upper bound

    //Way 2 :- Slightly optimizied as we maintain the empty cell List , although here tradeoff is with space
    Random random ;

    public MediumBotPlayingStrategy()
    {
        this.random = new Random();
    }
    @Override
    public Move makeMove(Board board, Player player)
    {
        int lengthOfEmptyCellList = board.getEmptyCellList().size();

        int num = random.nextInt(0,lengthOfEmptyCellList);

        Cell emptyCell = board.getEmptyCellList().get(num);

        return new Move(player,emptyCell);
    }
}
