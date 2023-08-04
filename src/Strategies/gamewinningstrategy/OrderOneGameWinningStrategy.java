package Strategies.gamewinningstrategy;

import models.Board;
import models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy{
    List<HashMap<Character,Integer>> rowsymbolcount = new ArrayList<>();
    List<HashMap<Character,Integer>> colsymbolcount = new ArrayList<>();
    HashMap<Character,Integer> lefttopsymbolcount= new HashMap<>();
    HashMap<Character,Integer> righttopsymbolcount= new HashMap<>();
    public OrderOneGameWinningStrategy(int dimension){
        {
            for(int i=0; i<dimension;i++){
                rowsymbolcount.add(new HashMap<Character,Integer>());
                colsymbolcount.add(new HashMap<Character,Integer>());
            }
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        char symbol= move.getPlayer().getSYMBOL();
        int row = move.getCell().getRow();
        int col= move.getCell().getCol();
        int dimension= board.getBoard().size();
        if(!rowsymbolcount.get(row).containsKey(symbol)){
            rowsymbolcount.get(row).put(symbol,0);
        }
        rowsymbolcount.get(row).put(symbol,rowsymbolcount.get(row).get(symbol)+1);
        if(!colsymbolcount.get(col).containsKey(symbol)){
            colsymbolcount.get(col).put(symbol,0);
        }
        colsymbolcount.get(col).put(symbol,colsymbolcount.get(col).get(symbol)+1);
        if (board.isTopLeftDiagnoal(row,col)){
            if(!lefttopsymbolcount.containsKey(symbol)){
                lefttopsymbolcount.put(symbol,0);
            }
            lefttopsymbolcount.put(symbol,lefttopsymbolcount.get(symbol)+1);
        }
        if (board.isTopRightDiagnoal(row,col)){
            if(!righttopsymbolcount.containsKey(symbol)){
                righttopsymbolcount.put(symbol,0);
            }
            righttopsymbolcount.put(symbol,righttopsymbolcount.get(symbol)+1);
        }
        if (rowsymbolcount.get(row).get(symbol).equals(dimension) || colsymbolcount.get(col).get(symbol).equals(dimension)){
            return  true;
        }
        if (lefttopsymbolcount.containsKey(symbol)) {
            if (lefttopsymbolcount.get(symbol).equals(dimension)) {
                return true;
            }
        }
        if (righttopsymbolcount.containsKey(symbol)) {
            if (righttopsymbolcount.get(symbol).equals(dimension)) {
                return true;
            }
        }
        return false;
    }
    public void UndoSymbolCount(Board board,Move move){
        char symbol= move.getPlayer().getSYMBOL();
        int row = move.getCell().getRow();
        int col= move.getCell().getCol();
        int dimension= board.getBoard().size();
        if(rowsymbolcount.get(row).containsKey(symbol)) {
            rowsymbolcount.get(row).put(symbol,rowsymbolcount.get(row).get(symbol)-1);
        }
        if(colsymbolcount.get(col).containsKey(symbol)){
            colsymbolcount.get(col).put(symbol,colsymbolcount.get(col).get(symbol)-1);
        }

        if (board.isTopLeftDiagnoal(row,col)){
            if(lefttopsymbolcount.containsKey(symbol)){
                lefttopsymbolcount.put(symbol,lefttopsymbolcount.get(symbol)-1);
            }

        }
        if (board.isTopRightDiagnoal(row,col)){
            if(righttopsymbolcount.containsKey(symbol)){
                righttopsymbolcount.put(symbol,righttopsymbolcount.get(symbol)-1);
            }

        }
    }
}
