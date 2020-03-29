package Exceptions;

public class InvalidBoardSizeException extends RuntimeException{
    public InvalidBoardSizeException(int boardSize){
        super("Dimensiune invalida a tablei:" + boardSize);
    }
}
