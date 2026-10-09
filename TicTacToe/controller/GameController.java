package controller;

import models.Game;
import models.Player;
import models.enums.GameState;
import models.enums.WinningStategyTypes;

import java.util.List;

//Controller will contain all the methods for which User will be communicating with the backend
//Controller functions are linked to API's call
public class GameController
{
    //start Game
    //@RestController("/subdomain/api/v1/startGame")
    public Game startGame(int dimension, List<Player> players, List<WinningStategyTypes> winningStategyTypes)
    {
        return Game.getBuilder().setPlayers(players)
                .setDimension(dimension)
                .setWinningStategyTypes(winningStategyTypes)
                .build();

    }


    //display board

    public void displayBoard(Game game)
    {
        //Internally using gameId first Game object is fetched from DB and then displayboard() called
        game.getBoard().displayBoard();
    }

    //get Game State

    public GameState getGameState(Game game)
    {
        return game.getGameState();
    }

    //make move
    //The reason why makeMove() is written in Game class because various input like playerlist , nextPlayerIndex
    //this all info we are getting from Game
    public void makeMove(Game game)
    {
        game.makeMove();
    }


    //undo
    public void undo(Game game)
    {
        game.undo();
    }

    //getWinner
    public Player getWinner(Game game)
    {
        return  game.getWinner();
    }
}
