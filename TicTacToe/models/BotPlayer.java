package models;

import factories.BotPlayingStrategyFactory;
import models.enums.BotDifficultyLevel;
import models.enums.PlayerType;
import strategies.botplayingstrategy.BotPlayingStrategy;

public class BotPlayer extends Player
{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol,PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board)
    {
        return botPlayingStrategy.makeMove(board,this);
    }
}
