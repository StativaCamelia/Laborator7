package PositionalGame;

import javax.jws.soap.SOAPBinding;
import java.util.Scanner;

public class ManualPlayer extends Player {
    boolean available = false;

    ManualPlayer(String name, Game game) {
        this.name = name;
        this.currentGame = game;
        game.addPlayer(this);
    }


    @Override
    public void run() {
        try {
            while (currentGame.isGameContinue()) {
                if (currentGame.gameBoard.getTokenList().isEmpty()) {
                    currentGame.setGameContinue(false);
                    Thread.currentThread().interrupt();
                    return;
                } else {
                    Token extractedToken = currentGame.extractFromBoard(-1);
                    System.out.println("Jucatorul:" + this.name + " a extras token-ul:" + extractedToken + " ");
                    tokenList.add(extractedToken);

                    if (tokenList.size() >= currentGame.getProgressionSize()) {
                        if (this.lenghtOfLongestProgression() >= currentGame.getProgressionSize()) {
                            System.out.println("Jucatorul" + name + "este castigator");
                            currentGame.setGameContinue(false);
                            score = this.currentGame.gameBoardSize;
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (score != this.currentGame.gameBoardSize) {
                score += lenghtOfLongestProgression();
            }
        }
    }

}