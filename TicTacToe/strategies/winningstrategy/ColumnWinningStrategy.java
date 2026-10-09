package strategies.winningstrategy;

import models.Board;
import models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy
{
    private List<Map<Character,Integer>> colMaps;

    public ColumnWinningStrategy(Board board)
    {
        this.colMaps = new ArrayList<>();
        for(int i=0;i<board.getDimension();i++)
        {
            colMaps.add(new HashMap<>());
        }
    }
    @Override
    public boolean checkWinnerAndUpdateCountMap(Board board, Move move)
    {
        int col = move.getCell().getY();

        Map<Character,Integer> map = colMaps.get(col);
        Character pChar = move.getPlayer().getSymbol().getSym();

        map.putIfAbsent(pChar,0);
        map.put(pChar,map.get(pChar)+1);

        if(map.get(pChar)==board.getDimension())
            return true;

        return false;
    }

    @Override
    public void undoCountMapUpdate(Move move,Board board)
    {
        int col = move.getCell().getY();

        Map<Character,Integer> map = colMaps.get(col);
        Character pChar = move.getPlayer().getSymbol().getSym();

        map.put(pChar,map.get(pChar)-1);
    }
}
