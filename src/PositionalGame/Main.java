package PositionalGame;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] main){

        Game game = new Game(10, 50,3);
        System.out.println("Introduceti numarul de playeri");
        Scanner scan = new Scanner(System.in);
        int numberOfPlayers = scan.nextInt() -1 ;
        scan.close();
        Player[] arrayOfPlayers = IntStream.rangeClosed(0, numberOfPlayers).mapToObj(i -> new Player("Player" + (i+1), game)).toArray(Player[]::new);

        game.initGame();

        Thread[] arrayOfThreads = IntStream.rangeClosed(0, numberOfPlayers).mapToObj(i -> new Thread(arrayOfPlayers[i])).toArray(Thread[]::new);

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

    }
}
