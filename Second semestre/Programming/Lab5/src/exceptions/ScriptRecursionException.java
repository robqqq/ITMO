package exceptions;

public class ScriptRecursionException extends RuntimeException{

    public ScriptRecursionException(String msg){
        super(msg);
    }
}
