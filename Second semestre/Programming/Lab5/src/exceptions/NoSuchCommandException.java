package exceptions;

public class NoSuchCommandException extends Exception{

    public NoSuchCommandException(String msg){
        super(msg);
    }
}
