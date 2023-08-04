package Strategies.botplayingstrategy;

import models.Board;
import models.Bot;
import models.Move;

public interface BotPlayingStrategy {
    public Move moveToMake(Board board, Bot bot);
}
