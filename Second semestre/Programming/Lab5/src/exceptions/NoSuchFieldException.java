package exceptions;

public class NoSuchFieldException extends RuntimeException{

    public NoSuchFieldException(String msg){
        super(msg);
    }
}
