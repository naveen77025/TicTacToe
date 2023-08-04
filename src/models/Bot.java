//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package models;

import Strategies.botplayingstrategy.BotPlayingStrategy;
import Strategies.botplayingstrategy.EasylevelBot;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, char symbol, PlayerType type, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, type);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy= new EasylevelBot();
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return this.botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move moveToMake(Board board) {

        return botPlayingStrategy.moveToMake(board,this);
    }
}
