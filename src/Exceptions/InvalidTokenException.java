package Exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(int number){
        super("Valoare invalida pentru token" + number);
    }
}
