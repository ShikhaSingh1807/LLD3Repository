package strategies.winningstrategy;

import models.Board;
import models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy
{
    private Map<Character,Integer> mainDiagonal;
    private Map<Character,Integer> antiDiagonal;

    public DiagonalWinningStrategy(Board board)
    {
        this.mainDiagonal = new HashMap<>();
        this.antiDiagonal = new HashMap<>();
    }
    @Override
    public boolean checkWinnerAndUpdateCountMap(Board board, Move move)
    {
        int row = move.getCell().getX();
        int col = move.getCell().getY();
        Character pChar = move.getPlayer().getSymbol().getSym();

        if(row==col)
        {
           mainDiagonal.putIfAbsent(pChar,0);
           mainDiagonal.put(pChar,mainDiagonal.get(pChar)+1);

            if(mainDiagonal.get(pChar)==board.getDimension())
                return true;
        }

        if(row+col==board.getDimension()-1)
        {
            antiDiagonal.putIfAbsent(pChar,0);
            antiDiagonal.put(pChar,antiDiagonal.get(pChar)+1);

            if(antiDiagonal.get(pChar)==board.getDimension())
                return true;
        }
        return false;
    }

    @Override
    public void undoCountMapUpdate(Move move,Board board)
    {
        int row = move.getCell().getX();
        int col = move.getCell().getY();
        Character pChar = move.getPlayer().getSymbol().getSym();

        if(row==col)
        {
           mainDiagonal.put(pChar,mainDiagonal.get(pChar)-1);
        }

        if(row + col == board.getDimension() - 1)
        {
            System.out.println("UNDO diagonal player = " + pChar);
            System.out.println("antiDiagonal = " + antiDiagonal);

            antiDiagonal.put(pChar, antiDiagonal.get(pChar) - 1);
        }

    }
}
