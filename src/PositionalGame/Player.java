package PositionalGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements Runnable{
    String name = new String();
    List<Token> tokenList = new ArrayList<>();
    Game currentGame;

    Player(String name, Game game){
        this.name = name;
        this.currentGame = game;
    }

    public void addToken(Token token){
        this.tokenList.add(token);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Player)) {
            return false;
        }
        Player other = (Player) obj;
        return name.equals(other.name);
    }

    public boolean verifyProgression(){
        Collections.sort(this.tokenList, (Object o1, Object o2) ->{
            Token p1 = (Token) o1;
            Token p2 = (Token) o2;
            if (p1.getTokenValue() < p2.getTokenValue()) return -1;
            if (p1.getTokenValue() > p2.getTokenValue()) return 1;
            return 0;
        });

        int step = Math.abs((tokenList.get(0).getTokenValue() -tokenList.get(1).getTokenValue()));
        for(int i=1; i< tokenList.size(); i++){
            if(Math.abs(tokenList.get(i).getTokenValue() -tokenList.get(i+1).getTokenValue()) != step){
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        try {
            while(currentGame.isGameContinue()){
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
                if(currentGame.gameBoard.getTokenList().isEmpty()){

                    currentGame.setGameContinue(false);
                    return;
                }
                else{
                    int randomPosition = (int)Math.floor(Math.random()*currentGame.getGameBoard().tokenList.size());
                    Token extractedToken = currentGame.gameBoard.tokenList.get(randomPosition);
                    System.out.println(extractedToken);
                    currentGame.extractFromBoard(extractedToken);
                    tokenList.add(extractedToken);
                    
                    if(tokenList.size() == currentGame.getProgressionSize()) {
                        if (verifyProgression()) {
                            System.out.println("Jucatorul" + name + "este castigator");
                            currentGame.setGameContinue(false);
                            return;
                        }
                    }
                }
            }
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        finally {
            System.out.println(tokenList);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
