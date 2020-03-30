package PositionalGame;

import Exceptions.InvalidBoardSizeException;
import Exceptions.InvalidTokenLimit;

import java.util.ArrayList;
import java.util.List;

public class Board {
    int boardSize;
    int tokenLimit;
    List<Token> tokenList = new ArrayList<>();

    /**
     *Daca dimensiunea board-ului sau a limitei superioare pentru tokens nu respecte conditiile (n> 1) si (m>1 si m< n) arunc o exceptie
     * @param boardSize
     * @param tokenLimit
     */
    Board(int boardSize, int tokenLimit){
        if(boardSize < 1)
            throw new InvalidBoardSizeException(boardSize);
        else this.boardSize = boardSize;
        if(tokenLimit < boardSize || tokenLimit < 1)
            throw  new InvalidTokenLimit(tokenLimit);
        else this.tokenLimit = tokenLimit;
    }

    Board(List<Token> tokenList, int boardSize, int tokenLimit){
        this.tokenList = tokenList;

        if(boardSize < 1)
            throw new InvalidBoardSizeException(boardSize);
        else this.boardSize = boardSize;
        if(tokenLimit > boardSize || tokenLimit < 1)
            throw  new InvalidTokenLimit(tokenLimit);
        else this.tokenLimit = tokenLimit;
    }

    /**
     * Initializez Board-ul cu valori random distincte din intervalul (1,m)
     */
    public void initBoard(){
        while(tokenList.size()!= boardSize)
         {
             int possibleValue = (int)Math.floor(Math.random()*tokenLimit) + 1;
             Token possibleToken = new Token(possibleValue, tokenLimit);
             this.addToken(possibleToken);
        }
    }

    private void addToken(Token tok){
        for(Token tokInd: tokenList){
            if(tokInd.equals(tok))
                return;
        }
        tokenList.add(tok);
    }

    @Override
    public String toString() {
        return "Asa arata board-ul:" + tokenList.toString();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        if(boardSize < 1)
            throw new InvalidBoardSizeException(boardSize);
        else
            this.boardSize = boardSize;
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }
}
