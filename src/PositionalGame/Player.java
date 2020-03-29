package PositionalGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements Runnable{
    String name = new String();
    List<Token> tokenList = new ArrayList<>();
    Game currentGame;
    int score = 0;

    Player(String name, Game game){
        this.name = name;
        this.currentGame = game;
        game.addPlayer(this);
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

    private int lenghtOfLongestProgression()
    {
        int[][] matrix = new int[tokenList.size()][tokenList.size()];
        int step = 2;
        if (tokenList.size() <= 2)
            return tokenList.size();
        for (int i = 0; i < tokenList.size(); i++)
            matrix[i][tokenList.size()-1] = 2;
        for (int j=tokenList.size()-2; j>=1; j--)
        {
            int i = j-1, k = j+1;
            while (i >= 0 && k <= tokenList.size()-1)
            {
                if (tokenList.get(i).getTokenValue() + tokenList.get(k).getTokenValue() < 2*tokenList.get(j).getTokenValue())
                    k++;
                else if (tokenList.get(i).getTokenValue() + tokenList.get(k).getTokenValue() > 2*tokenList.get(j).getTokenValue())
                {   matrix[i][j] = 2;
                        i--;
                }
                else
                {
                    matrix[i][j] = matrix[j][k] + 1;
                    if(step < matrix[i][j])
                        step = matrix[i][j];
                    i--;
                    k++;
                }
            }
            while (i >= 0)
            {
                matrix[i][j] = 2;
                i--;
            }
        }
        return step;
    }

    @Override
    public void run() {
        try {
            while(currentGame.isGameContinue()){
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 5000));
                if(currentGame.gameBoard.getTokenList().isEmpty()){
                    currentGame.setGameContinue(false);
                    return;
                }
                else{
                    int randomPosition = (int)Math.floor(Math.random()*currentGame.getGameBoard().tokenList.size());
                    Token extractedToken = currentGame.gameBoard.tokenList.get(randomPosition);
                    System.out.println(extractedToken);
                    currentGame.extractFromBoard(extractedToken);
                    System.out.println("Jucatorul:" + this.name + " a extras token-ul:" + extractedToken);
                    tokenList.add(extractedToken);

                    if(tokenList.size() >= currentGame.getProgressionSize()) {
                        if (lenghtOfLongestProgression() >= currentGame.getProgressionSize()) {
                            System.out.println("Jucatorul" + name + "este castigator" );
                            currentGame.setGameContinue(false);
                            score = this.currentGame.gameBoardSize;
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
            score = lenghtOfLongestProgression();
            System.out.println(tokenList);
        }
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
