package models;

import models.enums.PlayerType;
import validations.moveValidation.ValidateCellStatusAsEmpty;
import validations.moveValidation.ValidateCellWithinTheBoard;

import java.util.Scanner;

public class HumanPlayer extends Player
{
    private int age;
    private final Scanner scanner;

    public HumanPlayer(String name, Symbol symbol, int age) {
        super(name, symbol,PlayerType.HUMAN);
        this.age = age;
        this.scanner = new Scanner(System.in);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Move makeMove(Board board)
    {
        System.out.println("Enter the row where you want to make move : ");
        int row = scanner.nextInt();
        System.out.println("Enter the column where you want to make move : ");
        int col = scanner.nextInt();

        Move move = new Move(this,new Cell(row,col));
        //Validate the move - Human move can be wrong but bit not
        //1. Validate if the row and the col falls well with in the board dimension
        //2. Validate if the cell player is making move on is not Filled already
        ValidateCellWithinTheBoard.checkCellWithInBoardBoundary(move,board);
        ValidateCellStatusAsEmpty.checkCellStatus(move,board);

        return move;
    }
}
