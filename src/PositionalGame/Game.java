package PositionalGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    List<Player> playersList = new ArrayList<>();
    Board gameBoard;
    int gameBoardSize;
    int tokenLimit;
    int progressionSize;
    Scanner scan;

    volatile boolean gameContinue = true;
    boolean available = false;


    Game( int gameBoardSize, int tokenLimit, int progressionSize, Scanner scan){
        this.gameBoardSize = gameBoardSize;
        this.tokenLimit = tokenLimit;
        this.progressionSize = progressionSize;
        this.scan = scan;
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
     * @param
     */
    public synchronized Token extractFromBoard(int position) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        this.available = false;
        if(position != -1) {
            Token extractedToken = this.gameBoard.tokenList.get(position);
            this.gameBoard.getTokenList().remove(extractedToken);
            notifyAll();
            return extractedToken;
        }
        else{
            while (true) {
                System.out.println("Introduceti valoarea unui token din lista:" + this.getGameBoard().getTokenList().toString());
                int tokenValue = scan.nextInt();
                for (Token tok : this.gameBoard.tokenList) {
                    if (tokenValue == tok.getTokenValue()) {
                        Token extractedToken = tok;
                        this.gameBoard.getTokenList().remove(extractedToken);
                        return tok;
                    }
                }
                System.out.println("Valoarea introdusa nu corespunde unui token incercati iar:");
            }
        }
    }

    public void setGameContinue(boolean gameContinue) {
        this.gameContinue = gameContinue;
    }

    /**
     * Afiseaza punctajele finale ale jucatorilor
     */
    public void getWinners(){
        boolean winnerExists = false;
        for(Player player: playersList){
            if(player.getScore() == gameBoardSize )
                winnerExists = true;
        }
        if(winnerExists){
            for(Player player: playersList){
                if(player.getScore() != gameBoardSize)
                    player.setScore(0);
                System.out.println("Player-ul:" + player.getName()+ " are:" + player.getScore());
            }
        }
        else{
            for(Player player: playersList){
                System.out.println("Player-ul:" + player.getName()+ " are:" + player.getScore());
            }

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

