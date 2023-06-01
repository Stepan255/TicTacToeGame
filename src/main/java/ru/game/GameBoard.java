package ru.game;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class GameBoard {
    protected int sizeX;
    protected int sizeY;
    protected char[] board;

    public GameBoard(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new char[sizeX * sizeY];
        Arrays.fill(board, '*');
    }

    public boolean changeValueCell(int x, int y) {
        return false;
    }

    public char getCell(int x, int y) {
        if (x * y > board.length && x * y > 0) {
            return 404;
        }
        return board[(y - 1) * sizeX + (x - 1)];
    }

    public boolean setCell(int x, int y, char ch) {
        if (x * y > board.length || x * y <= 0) {
            return false;
        }
        board[(y - 1) * sizeX + (x - 1)] = ch;
        return true;

    }

    public boolean changeCellValue(int x, int y, char ch) {
        char cellValue = getCell(x, y);
        if (cellValue == '*') {
            return setCell(x, y, ch);
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder gameBoard = new StringBuilder("-");
        for (int i = 1; i <= sizeX; i++) {
            gameBoard.append("|").append(i);
        }
        gameBoard.append("|").append("\n");

        for (int j = 1; j <= sizeY; j++) {
            gameBoard.append(j);
            for (int i = 1; i <= sizeX; i++) {
                gameBoard.append("|").append(this.getCell(i, j));
            }
            gameBoard.append("|").append("\n");

        }
        return gameBoard.toString();
    }

    public boolean gameEnd(int sizeLineToWin) {


//        Win or Loose


        return true;
    }

    public boolean drawCheck() {
        for (char c : board) {
            if (c == '*') {
                return false;
            }
        }
        return true;
    }

    public boolean winCheck(int sizeLineToWin) {
        return lineCheck(sizeLineToWin) || diagonalCheck(sizeLineToWin);
    }

    private boolean diagonalCheck(int sizeLineToWin) {
        for (int i = 1; i <= sizeX; i++) {

        }
        return false;
    }

    private boolean mainDiagonalParallelCheck(int x, int y) {
        return false;
    }

    private boolean secondaryDiagonalParallelCheck(int x, int y) {
        return false;
    }

    private boolean lineCheck(int sizeLineToWin) {
        return horizontalLineCheckWin(sizeLineToWin) || verticalLineCheckWin(sizeLineToWin);
    }

    private boolean verticalLineCheckWin(int sizeLineToWin) {
        for (int i = 1; i <= sizeX; i++) {
            int countWin = 0;
            char previousChar = '*';
            for (int j = 1; j <= sizeY; j++) {
                System.out.println(i+" | "+j);
                char cell = getCell(i, j);
                countWin = coincidencePreviousChar(countWin, previousChar, cell);
                if (countWin >= sizeLineToWin) {
                    return true;
                }
                previousChar = cell;
            }
        }
        return false;
    }

    private boolean horizontalLineCheckWin(int sizeLineToWin) {
        for (int j = 1; j <= sizeY; j++) {
            int countWin = 0;
            char previousChar = '*';
            for (int i = 1; i <= sizeX; i++) {
                System.out.println(i+" | "+j);
                char cell = getCell(i, j);
                countWin = coincidencePreviousChar(countWin, previousChar, cell);
                if (countWin >= sizeLineToWin) {
                    return true;
                }
                previousChar = cell;
            }
        }
        return false;
    }

    private int coincidencePreviousChar(int countWin, char previousChar, char cell) {
        if (cell == previousChar && previousChar != '*') {
            countWin += countWin > 0 ? 1 : 2;
        } else {
            countWin = 0;
        }
//        System.out.print(cell + " | ");
//        System.out.println(previousChar);
        return countWin;
    }

    //поле
    //-|1|2|3|4|5|
    //-+-+-+-+-+-|
    //1|O|O|O|0|0|
    //-+-+-+-+-+-|
    //2|O|O|O|0|0|
    //-+-+-+-+-+-|
    //3|O|O|O|0|0|
    //-----------|
}
