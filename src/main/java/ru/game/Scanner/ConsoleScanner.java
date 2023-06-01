package ru.game.Scanner;

import java.util.Scanner;

public class ConsoleScanner {
    Scanner scanner;

    public String get(String title) {
        String text = "";
        do {
            System.out.print(title);
            scanner = new Scanner(System.in);
            text = scanner.next();
        } while (text.equals(""));
        return text;
    }

    public int getInt(String title) {
        int number = 0;
        do {
            String stringNumber = this.get(title);
            try {
                number = Integer.parseInt(stringNumber);
                return number;
            } catch (Exception e) {
                System.out.println("You input incorrect number!");
            }
        } while (true);
    }

    public int getValidInt(int min, int max) {
        return 0;
    }
}
