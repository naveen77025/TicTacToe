//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import controllers.GameController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Bot;
import models.BotDifficultyLevel;
import models.Game;
import models.GameState;
import models.Player;
import models.PlayerType;

public class TicTacToeGame {
    public TicTacToeGame() {
    }

    public static void main(String[] args) {
        System.out.println("game is starting ......");
//        System.out.println("......");
//        System.out.println("......");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter game dimension");
        int dimension = sc.nextInt();
        List<Player> players = new ArrayList();
        System.out.println("will there be any bots? y/n");
        String isbot = sc.next();
        int noOfhumanPlayers = dimension - 1;
        String name;
        if (isbot.equals("y")) {
            noOfhumanPlayers = dimension - 2;
            System.out.println("Enter name of bot");
            name = sc.next();
            System.out.println("Enter symbol for bot");
            String Symbol = sc.next();
            players.add(new Bot(name, Symbol.charAt(0), PlayerType.BOT, BotDifficultyLevel.EASY));
        }

        for(int i = 0; i < noOfhumanPlayers; ++i) {
            System.out.println("enter player name for :" + i + "1");
            name = sc.next();
            System.out.println("enter symbol for : " + i + "1");
            String symbol = sc.next();
            Player player = new Player(name, symbol.charAt(0), PlayerType.HUMAN);
            players.add(player);
        }
        //        Game game= Game.getBuilder()
//                        .setDimension(dimension)
//                        .setPlayers(players)
//                        .Build();
        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension, players);

        boolean intialmove= true;
        while(gameController.getGameState(game).equals(GameState.INPROGRESS)) {
            System.out.println("This is the current board");
            gameController.displayBoard(game);
            gameController.executeNextMove(game);
            gameController.displayBoard(game);
            if (game.getPlayers().get(game.getNextPlayerIndex()).getClass().getName().equals("models.Player")) {
                System.out.println("Do you want to undo? y/n");
                String Undomove = sc.next();
                if (Undomove.equals("y")) {
                    gameController.undo(game);
                }
                else{
                    game.UpdateNextPlayerindex();
                }
            }
            else{
                game.UpdateNextPlayerindex();
            }
        }

        if (gameController.getGameState(game).equals(GameState.DRAW)) {
            System.out.println("GAME has been DRAWN");
        }

        if (gameController.getGameState(game).equals(GameState.ENDED)) {
            System.out.println("Game has ended");
            System.out.println("winner is: "+ game.getWinner().getName());
        }

    }
}
