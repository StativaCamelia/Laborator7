package PositionalGame;

import Exceptions.InvalidBoardSizeException;
import Exceptions.InvalidTokenLimit;

import java.util.ArrayList;
import java.util.List;

public class Board {
    int boardSize;
    int tokenLimit;
    List<Token> tokenList = new ArrayList<>();

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

    public void initBoard(){
        for (int i = 1; i <=boardSize; i++) {
            this.tokenList.add(new Token(i,tokenLimit));
        }
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
