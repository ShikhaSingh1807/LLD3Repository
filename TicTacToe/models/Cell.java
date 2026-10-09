package models;

import models.enums.CellState;

public class Cell
{
    private int x;
    private int y;
    private CellState cellState;
    private Player player;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.cellState=CellState.EMPTY;
        this.player=null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void display()
    {
        if(this.cellState==CellState.EMPTY)
        {
            System.out.print("[ ]");
        }
        else if(this.cellState==CellState.FILLED)
        {
            System.out.print("["+this.player.getSymbol().getSym()+"]");
        }
    }
}
