package PositionalGame;

import Exceptions.InvalidTokenException;

public class Token {
    int tokenValue;
    int tokenLimit;

    /**
     * Daca valoarea asociata token-ului este mai mica decat 1 sau mai mare decat m arunca o exceptie
     * @param tokenValue
     * @param tokenLimit
     */
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

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Token)) {
            return false;
        }
        Token other = (Token) obj;
        return tokenValue == other.tokenValue;
    }
    @Override
    public String toString() {
        return String.format("%d",tokenValue);
    }
}
