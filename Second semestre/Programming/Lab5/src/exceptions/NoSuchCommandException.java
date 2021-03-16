package exceptions;

/**
 * Исключение при отсутсвии команды
 */
public class NoSuchCommandException extends Exception{

    public NoSuchCommandException(String msg){
        super(msg);
    }
}
