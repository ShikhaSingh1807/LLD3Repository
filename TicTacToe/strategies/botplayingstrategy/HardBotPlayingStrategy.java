package strategies.botplayingstrategy;

import models.Board;
import models.Move;
import models.Player;

// Minimax -
// AI Minimax -
// Prompt - "I have this interface in a Java TicTacToe project:
//
//public interface BotPlayingStrategy {
//    Move makeMove(Board board,Player player);
//}
//
//Board has getDimension() and getBoard() (a List<List<Cell>>). Cell
//has getCellState() (EMPTY or FILLED), getRow(), getCol(), and
//getPlayer(). Move has a constructor Move(Player player, Cell cell).
//
//Implement HardBotPlayingStrategy using minimax: take a winning
//move immediately if one exists, block the opponent's winning move
//if they have one, and otherwise pick the best move assuming the
//opponent always plays optimally. Match the interface exactly —
//return a Move with player set as player sent to method"

// Minimax algo visualiser - https://acad-backend-vizs.vercel.app/instructor-notes-machine-coding-4-code-tictactoe-2/minimax-tree-exploration-step-through-minimax-tree-builder/v2/
// Minimax algo (explosion problem) - https://acad-backend-vizs.vercel.app/instructor-notes-machine-coding-4-code-tictactoe-2/minimax-explosion-on-larger-boards/v2/

// Solution - depth-limiting
//TC :- O(b^m) => here m is depth of tree and b is branching factor of Game tree
//SC :- O(b^m)
public class HardBotPlayingStrategy implements BotPlayingStrategy
{

    @Override
    public Move makeMove(Board board, Player player) {
        return null;
    }
}
