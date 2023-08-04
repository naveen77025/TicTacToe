//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package models;

import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;
    private PlayerType type;

    public Player(String name, char symbol, PlayerType type) {
        this.name = name;
        this.symbol = symbol;
        this.type = type;
    }
    public Move moveToMake(Board board){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter row to make a move");
        int row= scanner.nextInt();
        System.out.println("Enter col to make a move");
        int col = scanner.nextInt();
        return new Move(new Cell(row,col),this);
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSYMBOL() {
        return this.symbol;
    }

    public void setSYMBOL(char SYMBOL) {
        this.symbol = SYMBOL;
    }

    public PlayerType getType() {
        return this.type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }
}
