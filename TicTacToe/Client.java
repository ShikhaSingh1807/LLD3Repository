import controller.GameController;
import models.*;
import models.enums.BotDifficultyLevel;
import models.enums.GameState;
import models.enums.WinningStategyTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client
{
    static void main()
    {
        Scanner sc = new Scanner(System.in);
        int dimension = 3;

        //In Rest API we will not be able to send the List of objects
        //Rather we will have a User Controller , using which when a user register on the application a user id
        // generated for them.
        //Here we will send startGame(dimension , List.of(userId1,userId2))
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer("Shikha",new Symbol('X'),23));
        players.add(new BotPlayer("Botty",new Symbol('O'), BotDifficultyLevel.EASY));

        List<WinningStategyTypes> winningStategyTypes = List.of(WinningStategyTypes.ROW,WinningStategyTypes.COLUMN,
                WinningStategyTypes.DIAGONAL);

        GameController gameController = new GameController();
        Game game = gameController.startGame(dimension,players,winningStategyTypes);

        gameController.displayBoard(game);

        while(gameController.getGameState(game)== GameState.IN_PROGRESS)
        {
            gameController.makeMove(game);

            gameController.displayBoard(game);

            System.out.println("Do you want to undo the last move[y/n] : ");
            char answer = sc.nextLine().charAt(0);

            if(answer=='y' || answer=='Y')
            {
                gameController.undo(game);
            }
        }

        if(gameController.getGameState(game)==GameState.WIN)
        {
            System.out.println("Congratulation !!! "+gameController.getWinner(game).getName()+ " won the game.");
        }
        else if(gameController.getGameState(game)==GameState.DRAW)
        {
            System.out.println("Game got Draw , Better Luck Next Time!!!");
        }

        gameController.displayBoard(game);

    }
}
