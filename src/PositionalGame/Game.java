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

    /**
     * Returneaza statusul curent al jocului, daca continua sau daca s-a incheiat
     * @return
     */
    public boolean isGameContinue() {
        return gameContinue;
    }

    /**
     * Initializeaza jocul creend board-ul pe care acesta se va juca
     */
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

    /**
     * Metoda este syncronized pentru a nu permite apelurilor din partea unor user diferiti sa se suprapuna
     * Daca metoda este available ea va putea fi folosita de un jucator in cadrul jocului, in caz contrar se asteapta daca aceasta devine available
     * Cat timp un anume thread utilizeaza metoda aceasta este marcata ca nefiind available, la finalul executiei metodei toate thread-urile sunt anuntate
     * ca metoda este disponibila si poate fi utilizata
     * @param extractedToken
     */
    public synchronized void extractFromBoard(Token extractedToken) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        this.available = false;
        this.gameBoard.getTokenList().remove(extractedToken);
        notifyAll();
    }

    public void setGameContinue(boolean gameContinue) {
        this.gameContinue = gameContinue;
    }

    /**
     * Afiseaza punctajele finale ale jucatorilor
     */
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

