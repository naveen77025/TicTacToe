//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controllers;

import java.util.List;
import models.Game;
import models.GameState;
import models.Player;

public class GameController {
    public GameController() {
    }

    public Game createGame(int dimension, List<Player> players) {
        Game game = Game.getBuilder().setDimension(dimension).setPlayers(players).Build();
        return game;
    }

    public void undo(Game game) {
        game.undoMove(game);
    }

    public void executeNextMove(Game game) {
        game.executeNextMove(game.getBoard());
    }

    public Player getwinner(Game game) {
        return game.getWinner();
    }

    public Enum<GameState> getGameState(Game game) {
        return game.getGameState();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }
}
