package exceptions;

/**
 * Исключение, для некорректного поля объекта
 */
public class InvalidFieldException extends Exception{

    public InvalidFieldException(String msg) {
        super(msg);
    }
}
