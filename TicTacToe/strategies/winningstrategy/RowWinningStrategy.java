package strategies.winningstrategy;

import models.Board;
import models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy
{
    private List<Map<Character,Integer>> rowMaps;

    public RowWinningStrategy(Board board)
    {
        this.rowMaps = new ArrayList<>();
        for(int i=0;i<board.getDimension();i++)
        {
            rowMaps.add(new HashMap<>());
        }
    }
    @Override
    public boolean checkWinnerAndUpdateCountMap(Board board, Move move)
    {
        int row = move.getCell().getX();

        Map<Character,Integer> map = rowMaps.get(row);
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
        int row = move.getCell().getX();

        Map<Character,Integer> map = rowMaps.get(row);
        Character pChar = move.getPlayer().getSymbol().getSym();

        map.put(pChar,map.get(pChar)-1);
    }
}
