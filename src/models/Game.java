//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package models;

import Strategies.gamewinningstrategy.GameWinningStrategy;
import Strategies.gamewinningstrategy.OrderOneGameWinningStrategy;
import exceptions.InvalidNumberOfplayersException;
import exceptions.InvalidgameDimensionException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private int nextPlayerIndex;
    private GameState gameState;
    private Player winner;
    private GameWinningStrategy gameWinningStrategy;

    public Game() {
    }

    public static Bulder getBuilder() {
        return new Bulder();
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayerIndex() {
        return this.nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return this.winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void displayBoard() {
        this.board.displayBoard();
    }
    public void executeNextMove(Board board){
        // letting user to make move
        Player playerToPlay = players.get(nextPlayerIndex);
        System.out.println("it is "+ playerToPlay.getName()+"'s turn to play");
        Move move= playerToPlay.moveToMake(board);
        int row= move.getCell().getRow();
        int col= move.getCell().getCol();
        //validating the move
        if (board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)){
            System.out.println("Cell is already filled");
            System.out.println("Try another cell");
            move= playerToPlay.moveToMake(board);
            row= move.getCell().getRow();
            col= move.getCell().getCol();
        }
        else{
            board.applyMove(move);
            moves.add(move);
            //check the winner
            if(gameWinningStrategy.checkWinner(board,move)){
                this.setGameState(GameState.ENDED);
                this.setWinner(playerToPlay);
            }

        }
    }
    public void UpdateNextPlayerindex(){
        nextPlayerIndex+=1;
        nextPlayerIndex%=this.players.size();
        board.checkGameDraw(this);
    }
    public void undoMove(Game game){
        Move previousmove= game.moves.get(moves.size()-1);
        board.undoMove(previousmove);
        gameWinningStrategy.UndoSymbolCount(board,previousmove);

    }
    public static class Bulder {
        private int dimension;
        private List<Player> players;

        public Bulder() {
        }

        public Bulder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Bulder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean isValid() throws InvalidNumberOfplayersException, InvalidgameDimensionException {
            if (this.players.size() != this.dimension - 1) {
                throw new InvalidNumberOfplayersException("Number of players should be one less than dimension");
            } else if (this.dimension < 3) {
                throw new InvalidgameDimensionException("Dimension should atleast 3");
            } else {
                return true;
            }
        }

        public Game Build() {
            try {
                this.isValid();
            } catch (InvalidNumberOfplayersException var2) {
                System.out.println(var2.getMessage());
                return null;
            } catch (InvalidgameDimensionException var3) {
                System.out.println(var3.getMessage());
                return null;
            }

            Game game = new Game();
            game.setGameState(GameState.INPROGRESS);
            game.setPlayers(this.players);
            game.setBoard(new Board(this.dimension));
            game.setMoves(new ArrayList());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));
            return game;
        }
    }
}
