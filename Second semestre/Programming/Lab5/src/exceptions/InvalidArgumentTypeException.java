package exceptions;

/**
 * Исключение для неверного типа данных аргумента
 */
public class InvalidArgumentTypeException extends Exception{

    public InvalidArgumentTypeException(String msg){
        super(msg);
    }
}
