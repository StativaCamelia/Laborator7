package PositionalGame;

import java.util.Scanner;

public class UserInput {
    boolean available = false;
    Game currentGame;
    Scanner scan;

    UserInput(Game currentGame, Scanner scan){
        this.currentGame = currentGame;
        this.scan = scan;
    }



}
