package validations.moveValidation;

import exceptions.InvalidMoveException;
import models.Board;
import models.Move;

public class ValidateCellWithinTheBoard
{
    public static void checkCellWithInBoardBoundary(Move move, Board board)
    {
        int row = move.getCell().getX();
        int col = move.getCell().getY();

        if((row<0 || row> board.getDimension()-1) || (col<0 || col > board.getDimension()-1))
        {
            throw new InvalidMoveException("Row or Column index is invalid...");
        }
    }
}
