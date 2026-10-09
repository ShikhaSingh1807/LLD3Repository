package strategies.winningstrategy;

import models.Board;
import models.Move;

public interface WinningStrategy
{
    boolean checkWinnerAndUpdateCountMap(Board board, Move move);
    void undoCountMapUpdate(Move move,Board board);
}
