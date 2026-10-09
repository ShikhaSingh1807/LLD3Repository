package validations.moveValidation;

import exceptions.InvalidMoveException;
import models.Board;
import models.Move;
import models.enums.CellState;

public class ValidateCellStatusAsEmpty
{
    public static void checkCellStatus(Move move , Board board)
    {
        int row = move.getCell().getX();
        int col = move.getCell().getY();

        if(board.getGrid().get(row).get(col).getCellState()== CellState.FILLED)
            throw new InvalidMoveException("Cell is already filled...");
    }
}
