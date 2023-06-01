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

    public boolean checkCell(int x, int y) {
        int index = (y - 1) * sizeX + (x - 1);
        return index >= 0 && index < board.length;
    }

    //TODO Обработать ошибку 404
    public char getCell(int x, int y) {
        int index = (y - 1) * sizeX + (x - 1);
        if (index < 0 || index >= board.length) {
            throw new RuntimeException();
        }
        return board[index];
    }

    public boolean setCell(int x, int y, char ch) {
        int index = (y - 1) * sizeX + (x - 1);
        if (index < 0 || index >= board.length || board[index] != '*') {
            return false;
        }
        board[index] = ch;
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

    public boolean drawCheck() {
        for (char c : board) {
            if (c == '*') {
                return false;
            }
        }
        return true;
    }

    public boolean winCheck(int sizeLineToWin) {
        return lineCheck(sizeLineToWin) || diagonalCheckWin(sizeLineToWin);
    }

    private boolean diagonalCheckWin(int sizeLineToWin) {
        for (int j = sizeY; j >= 1; j--) {
            int i = 1;
            if (mainDiagonalParallelCheck(i, j, sizeLineToWin) || secondaryDiagonalParallelCheck(i, j, sizeLineToWin)) {
                return true;
            }
        }

        for (int i = 1; i <= sizeX; i++) {
            int j = 1;
            if (mainDiagonalParallelCheck(i, j, sizeLineToWin) || secondaryDiagonalParallelCheck(i, j, sizeLineToWin)) {
                return true;
            }
        }

        for (int j = 1; j <= sizeY; j++) {
            int i = sizeX;
            if (mainDiagonalParallelCheck(i, j, sizeLineToWin) || secondaryDiagonalParallelCheck(i, j, sizeLineToWin)) {
                return true;
            }
        }
        return false;
    }

    private boolean mainDiagonalParallelCheck(int x, int y, int sizeLineToWin) {
        int countWin = 0;
        char previousChar = '*';
        while (x <= sizeX && y <= sizeY) {
            char cell = getCell(x++, y++);
            countWin = coincidencePreviousChar(countWin, previousChar, cell);
            if (countWin >= sizeLineToWin) {
                return true;
            }
            previousChar = cell;
        }
        return false;
    }

    private boolean secondaryDiagonalParallelCheck(int x, int y, int sizeLineToWin) {
        int countWin = 0;
        char previousChar = '*';
        while (x >= 1 && y <= sizeY) {
            if (checkCell(x, y)) {
                char cell = getCell(x--, y++);
                countWin = coincidencePreviousChar(countWin, previousChar, cell);
                if (countWin >= sizeLineToWin) {
                    return true;
                }
                previousChar = cell;
            } else {
                return false;
            }
        }
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
        return countWin;
    }

}
