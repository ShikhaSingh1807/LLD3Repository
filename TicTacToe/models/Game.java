package models;

import factories.WinningStrategyFactory;
import models.enums.CellState;
import models.enums.GameState;
import models.enums.WinningStategyTypes;
import strategies.winningstrategy.*;
import validations.gameValidation.NumberOfBotValidation;
import validations.gameValidation.NumberOfPlayerValidation;
import validations.gameValidation.UniqueSymbolValidation;


import java.util.ArrayList;
import java.util.List;

public class Game
{
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private Player winner;
    private int nextPlayerIndex;
    private List<Move> moves;
    private List<WinningStrategy> winningStategies;

    private Game(GameBuilder builder)
    {
        this.board = new Board(builder.getDimension());
        this.players = builder.getPlayers();
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.winningStategies = new ArrayList<>();

        for(WinningStategyTypes winningStategyTypes : builder.getWinningStategyTypes())
        {
           winningStategies.add(WinningStrategyFactory.getWinningStrategy(winningStategyTypes,board));
        }
    }

    public Board getBoard() {
        return board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public static GameBuilder getBuilder()
    {
        return new GameBuilder();
    }

    public void makeMove()
    {
        // 1. On the basis of next playeri index find out player who will making move
        Player player = players.get(nextPlayerIndex);
        System.out.println();
        System.out.println(player.getName()+" will make a move now");

        //2. Player will makeMove() but this will be the abstract method in Player as human and Bot
        //will play in different manner
        Move move =player.makeMove(board);


        //3.Update the move on the board
        int row = move.getCell().getX();//Just for information
        int col = move.getCell().getY();//Just for information

        Cell cell = board.getGrid().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(move.getPlayer());

        //4.Remove the cell from the emptyCellList
        //Below approach of removing the cell from the list is O(N^2) in worst case if N is board dimension
        //your emptyCellList can contain up to N² cells and ArrayList.remove(Object) has to search for the object first.

        //this.getBoard().getEmptyCellList().remove(cell);
        //Better approach is to get the index from the cell info and swap it with last element in List

        board.removeEmptyCell(cell);

        //5.If the move is valid add it in List<Move>

        moves.add(move);
        //6.Check Winner
        //loop through all the winning strategies selected by the players
        //6.1 --> Call for all the selected strategies by players after each move

        for(WinningStrategy strategy : winningStategies){
            if(strategy.checkWinnerAndUpdateCountMap(this.board, move)){
                //update the board status and winner
                this.gameState = GameState.WIN;
                this.winner = move.getPlayer();
            }
        }
        //6.2 Also Check if size of List<Move> is N*N then the board is full
        //7.If winner update status of game to WIN and also update winner details and if the board is full and no winner than update as DRAW
        // check draw - update game status accordingly
        if(moves.size()== board.getDimension()* board.getDimension())
        {
            setGameState(GameState.DRAW);
        }

        // 8. Move to the next player only if game is still running
        if (gameState == GameState.IN_PROGRESS)
        {
            nextPlayerIndex =
                    (nextPlayerIndex + 1) % players.size();
        }

    }

    public void undo()
    {
        //Take the last move from the Move List
        Move lastMove = moves.removeLast();

        //Go and remove the symbol from the cell where last move was made from the board4
        int row = lastMove.getCell().getX();
        int col = lastMove.getCell().getY();

        Cell cell = board.getGrid().get(row).get(col);
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        // IMPORTANT:
        // Since this cell is empty again, add it back
        // to emptyCellList and cellToIndexMap.
        board.addEmptyCell(cell);


        //Update the gameStatus and Winner status
        setGameState(GameState.IN_PROGRESS);
        setWinner(null);
        //Now perform the undo
        for(WinningStrategy winningStrategy : winningStategies)
        {
            winningStrategy.undoCountMapUpdate(lastMove,board);
        }

        //move the nextPlayerIndex to last player - decrement next player index
        //Mod Arithmetic - (a-b)%m = ((a%m) -(b%m) +m)%m
        nextPlayerIndex = ((nextPlayerIndex-1)+players.size())%players.size();
    }

    public static class GameBuilder
   {
       private int dimension;
       private List<Player> players;
       private List<WinningStategyTypes> winningStategyTypes;


       public int getDimension() {
           return dimension;
       }

       public GameBuilder setDimension(int dimension) {
           this.dimension = dimension;
           return  this;
       }

       public List<Player> getPlayers() {
           return players;
       }

       public GameBuilder setPlayers(List<Player> players) {
           this.players = players;
           return  this;
       }

       public List<WinningStategyTypes> getWinningStategyTypes() {
           return winningStategyTypes;
       }

       public GameBuilder setWinningStategyTypes(List<WinningStategyTypes> winningStategyTypes) {
           this.winningStategyTypes = winningStategyTypes;
           return  this;
       }

       public Game build()
       {
           //Validations
           //Checking for total number of player in the game
           NumberOfPlayerValidation.checkNumberOfPlayersInGame(players,dimension);

           //Checking for number of Bot to be exactly 1 or 0 and not more than that allowed
           NumberOfBotValidation.checkNumberOfBot(players);

           //Unique Symbol for each Player Validation
           UniqueSymbolValidation.checkUniqueSymbolsForPlayers(players);
           return new Game(this);
       }
   }

}
