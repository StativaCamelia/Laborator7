package Exceptions;

public class InvalidTokenLimit extends RuntimeException{
    public InvalidTokenLimit(int value){
        super("Limita superioara pentru valoarea tokens nu este valida");
    }
}
