package PositionalGame;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Player> playersList = new ArrayList<>();
    Board gameBoard;
    int gameBoardSize;
    int tokenLimit;
    int progressionSize;
    boolean gameContinue = true;
    boolean available = false;


    Game( int gameBoardSize, int tokenLimit, int progressionSize){
        this.gameBoardSize = gameBoardSize;
        this.tokenLimit = tokenLimit;
        this.progressionSize = progressionSize;
    }

    Game(List<Player> playersList, int gameBoardSize){
        this.playersList = playersList;
        this.gameBoardSize = gameBoardSize;
    }

    Game(List<Player> playersList, Board gameBoard){
        this.playersList = playersList;
        this.gameBoard = gameBoard;
        this.gameBoardSize = gameBoard.getBoardSize();
    }


    public boolean isGameContinue() {
        return gameContinue;
    }

    public void initGame(){
        gameBoard = new Board(gameBoardSize, tokenLimit);
        gameBoard.initBoard();
    }

    public void addPlayer(Player player){
        for(Player playerInd: playersList){
            if(playerInd.equals(player))
                System.out.println("Un player nu poate participa de doua ori la acelasi Game");
        }
        playersList.add(player);
    }

    public synchronized void extractFromBoard(Token extractedToken) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        this.available = false; notifyAll();
        this.gameBoard.getTokenList().remove(extractedToken);
    }

    public void setGameContinue(boolean gameContinue) {
        this.gameContinue = gameContinue;
    }

    public void getWinners(){
        for(Player player: playersList){
            System.out.println(player.getName() + "a obtinut:" + player.getScore());
        }
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public void setGameBoardSize(int gameBoardSize) {
        this.gameBoardSize = gameBoardSize;
    }

    public int getGameBoardSize() {
        return gameBoardSize;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public int getProgressionSize() {
        return progressionSize;
    }

    public void setProgressionSize(int progressionSize) {
        this.progressionSize = progressionSize;
    }

    public int getTokenLimit() {
        return tokenLimit;
    }



}

