package factories;

import models.Board;
import models.enums.BotDifficultyLevel;
import strategies.botplayingstrategy.BotPlayingStrategy;
import strategies.botplayingstrategy.EasyBotPlayingStrategy;
import strategies.botplayingstrategy.HardBotPlayingStrategy;
import strategies.botplayingstrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory
{
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel)
    {
        return switch (botDifficultyLevel)
        {

            case EASY -> new EasyBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
        };
    }
}
