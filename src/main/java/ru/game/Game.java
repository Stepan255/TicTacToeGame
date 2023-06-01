package ru.game;

import ru.game.Scanner.ConsoleScanner;
import ru.game.Scanner.GUI;

import java.util.Random;

public class Game {
    protected Player player1;
    protected Player player2;
    protected int sizeLineToWin;
    protected GameBoard gameBoard;
    protected Player playerTurn;


    public Game(Player player1, Player player2, int sizeLineToWin, GameBoard gameBoard) {
        this.player1 = player1;
        this.player2 = player2;
        this.sizeLineToWin = sizeLineToWin;
        this.gameBoard = gameBoard;
    }

    public void gameStart() {
        Random random = new Random();
        playerTurn = random.nextInt(2) == 1 ? player1 : player2;
        while (true) {
            System.out.println(gameBoard);
            playerMove();
            if (gameEnd()) {
                break;
            }
            changTurn();
        }
        if (playerTurn == null) {
            System.out.println("Ничья");
        }
        System.out.println(playerTurn.name + "win!!!");
    }

    private void changTurn() {
        if (playerTurn.equals(player1)) {
            playerTurn = player2;
        } else {
            playerTurn = player1;
        }

    }

    private boolean gameEnd() {
        if (gameBoard.winCheck(sizeLineToWin)) {
            return true;
        }
        if (gameBoard.drawCheck()) {
            playerTurn.setName(null);
            return true;
        }
        return false;
    }


    private void playerMove() {
        GUI inter = new GUI(new ConsoleScanner());
        int[] cell;
        do {
            cell = inter.getCell();
        } while (gameBoard.setCell(cell[0], cell[1], playerTurn.getSign()));

    }


}