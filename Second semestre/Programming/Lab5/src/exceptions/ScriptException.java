package exceptions;

/**
 * Исключение при ошибке в скрипте
 */
public class ScriptException extends RuntimeException{

    public ScriptException(String msg){
        super(msg);
    }
}
