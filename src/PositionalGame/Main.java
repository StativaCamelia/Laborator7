package PositionalGame;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] main){

        Scanner scan = new Scanner(System.in);
        Game game = new Game(100, 120,3, scan);
        System.out.println("Introduceti numarul de playeri");

        int numberOfPlayers = scan.nextInt();
        Player[] arrayOfPlayers = new Player[numberOfPlayers];

        System.out.println("Introduceti durata de desfasurare a meciului in secunde:");
        int durata = scan.nextInt();
        scan.nextLine();

        UserInput input = new UserInput(game, scan);

        for(int i=0; i< numberOfPlayers; i++) {
            if (Math.random() <= 0.5)
                arrayOfPlayers[i] = new ManualPlayer("Player" + i, game);
            else
                arrayOfPlayers[i] = new RandomPlayer("Player" + i, game);
        }

        game.initGame();

        Thread[] arrayOfThreads = IntStream.range(0, numberOfPlayers).mapToObj(i -> new Thread(arrayOfPlayers[i])).toArray(Thread[]::new);

        Thread dt = new Thread(new DaemonTimer(game, durata), "dt");
        dt.setDaemon(true);
        dt.start();
        for(Thread thread : arrayOfThreads){
           thread.start();
        }

        try {
            for (Thread thread : arrayOfThreads) {
                thread.join();
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        game.getWinners();
        scan.close();

    }
}
