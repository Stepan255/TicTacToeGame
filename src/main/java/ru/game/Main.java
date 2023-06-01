package ru.game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Player("Stepan", 'X'),
                new Player("bot", 'O'), 4,
                new GameBoard(5, 5));

        game.gameStart();

    }
}