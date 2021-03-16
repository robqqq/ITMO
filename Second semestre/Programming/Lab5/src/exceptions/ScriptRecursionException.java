package exceptions;

/**
 * Исключение, когда скрипты вызывают рекурсию
 */
public class ScriptRecursionException extends RuntimeException{

    public ScriptRecursionException(String msg){
        super(msg);
    }
}
