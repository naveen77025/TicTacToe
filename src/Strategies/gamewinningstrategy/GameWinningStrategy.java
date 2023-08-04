package Strategies.gamewinningstrategy;

import models.Board;
import models.Move;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Move move);
    void UndoSymbolCount(Board board,Move move);
}
