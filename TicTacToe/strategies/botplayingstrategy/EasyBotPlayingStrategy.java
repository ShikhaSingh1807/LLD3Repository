package strategies.botplayingstrategy;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;
import models.enums.CellState;

public class EasyBotPlayingStrategy implements BotPlayingStrategy
{


    //TC :- O(N^2)
    //SC :- O(1)
    @Override
    public Move makeMove(Board board, Player player)
    {
        for(int i=0;i<board.getDimension();i++)
        {
            for(int j=0;j<board.getDimension();j++)
            {
                if(board.getGrid().get(i).get(j).getCellState()== CellState.EMPTY)
                {
                    Move move = new Move(player,new Cell(i,j));
                    return move;
                }
            }
        }
        return  null;
    }
}
