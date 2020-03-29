package PositionalGame;

public class Main {
    public static void main(String[] main){

        Game game = new Game(10, 13,5);
        game.initGame();
        Player player1 = new Player("player1", game);
        Player player2 = new Player("player2", game);

        Thread player1Thread = new Thread(player1);
        Thread player2Thread = new Thread(player2);

        player1Thread.start();
        player2Thread.start();
    }
}
