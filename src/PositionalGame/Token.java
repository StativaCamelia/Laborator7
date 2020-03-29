package PositionalGame;

import Exceptions.InvalidTokenException;

import java.util.Comparator;

public class Token {
    int tokenValue;
    int tokenLimit;


    Token(int tokenValue, int tokenLimit){
        if(tokenValue < 1 || tokenValue > tokenLimit) throw new InvalidTokenException(tokenValue);
        else
        this.tokenValue = tokenValue;
    }



    public int getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(int tokenValue) {
        if(tokenValue < 1) throw new InvalidTokenException(tokenValue);
        else
            this.tokenValue = tokenValue;
    }

    @Override
    public String toString() {
        return String.format("%d",tokenValue);
    }
}
