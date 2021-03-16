package exceptions;

/**
 * Исключение при отсутсвии аргумента у команды
 */
public class NoArgException extends Exception{

    public NoArgException(String msg){
        super(msg);
    }
}
