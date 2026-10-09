package models;

import models.enums.CellState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board
{
    private int dimension;
    private List<List<Cell>> grid;
    private List<Cell> emptyCellList;
    private Map<Cell,Integer> cellToIndexMap;

    public Board(int dimension)
    {
        this.dimension = dimension;
        this.grid = new ArrayList<>();

        for(int i=0;i<dimension;i++)
        {
            grid.add(new ArrayList<>());

            for(int j=0;j<dimension;j++)
            {
                grid.get(i).add(new Cell(i,j));
            }
        }

        this.emptyCellList = new ArrayList<>();
        this.cellToIndexMap = new HashMap<>();

        int counter = 0;
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
                emptyCellList.add(grid.get(i).get(j));
                cellToIndexMap.putIfAbsent(grid.get(i).get(j),counter++);
            }
        }

    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    public List<Cell> getEmptyCellList() {
        return emptyCellList;
    }

    public void setEmptyCellList(List<Cell> emptyCellList) {
        this.emptyCellList = emptyCellList;
    }

    public Map<Cell, Integer> getCellToIndexMap() {
        return cellToIndexMap;
    }

    public void setCellToIndexMap(Map<Cell, Integer> cellToIndexMap) {
        this.cellToIndexMap = cellToIndexMap;
    }

    //Display the board
    public void displayBoard()
    {
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
                this.grid.get(i).get(j).display();
            }
            System.out.println();
        }
    }

    public void removeEmptyCell(Cell cell)
    {
        int indexOfCellToRemove = cellToIndexMap.get(cell); //7

        int lastElementIndex = emptyCellList.size()-1; //8

        Cell lastCell = emptyCellList.get(lastElementIndex);//new Cell[2,2]

        emptyCellList.set(indexOfCellToRemove,lastCell);//replace cell at 7th post with last element cell
        //replaces Cell[2,1] with Cell[2,2]

        cellToIndexMap.put(lastCell,indexOfCellToRemove);
        //Make the last cell Cell[2,2] in empty list with value as index we wanted to remove

        emptyCellList.remove(lastElementIndex);
        cellToIndexMap.remove(cell);
    }

    // Cell is becoming EMPTY again because of undo
    // Add it back to available/empty cells
    public void addEmptyCell(Cell cell)
    {
        int index = emptyCellList.size();

        emptyCellList.add(cell);

        cellToIndexMap.put(cell, index);
    }
}
