package factories;

import models.Board;
import models.enums.WinningStategyTypes;
import strategies.winningstrategy.ColumnWinningStrategy;
import strategies.winningstrategy.DiagonalWinningStrategy;
import strategies.winningstrategy.RowWinningStrategy;
import strategies.winningstrategy.WinningStrategy;

public class WinningStrategyFactory
{
    public static WinningStrategy getWinningStrategy(WinningStategyTypes winningStategyTypes, Board board)
    {
        return switch (winningStategyTypes)
        {

            case ROW -> new RowWinningStrategy(board);
            case COLUMN -> new ColumnWinningStrategy(board);
            case DIAGONAL -> new DiagonalWinningStrategy(board);
        };
    }
}
