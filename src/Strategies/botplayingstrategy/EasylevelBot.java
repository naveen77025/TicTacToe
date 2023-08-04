package Strategies.botplayingstrategy;

import models.*;

public class EasylevelBot implements BotPlayingStrategy {
    @Override
    public Move moveToMake(Board board, Bot bot) {
        int dimension= board.getBoard().size();
        for (int i=0;i<dimension;i++){
            for (int j=0;j<dimension;j++){
                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    return new Move(new Cell(i,j),bot);
                }
            }
        }
        return null;
    }
}
