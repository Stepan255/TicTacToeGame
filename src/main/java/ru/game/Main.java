package ru.game;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Stepan", 'X');
        Player bot = new Player("bot", 'O');
        GameBoard gameBoard = new GameBoard(5, 5);

        Game game = new Game(player, bot, 4, gameBoard);

        game.gameStart();

    }
}