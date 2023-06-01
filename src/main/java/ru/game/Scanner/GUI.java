package ru.game.Scanner;

public class GUI {
    ConsoleScanner consoleScanner;

    public GUI(ConsoleScanner consoleScanner) {
        this.consoleScanner = consoleScanner;
    }

    public int[] getCell() {
        ConsoleScanner cs = new ConsoleScanner();
        int x = cs.getInt("Input number x: ");
        int y = cs.getInt("Input number y: ");
        return new int[]{x, y};
    }
}
