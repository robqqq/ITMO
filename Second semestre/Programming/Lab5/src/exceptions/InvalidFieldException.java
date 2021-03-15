package exceptions;

/**
 * Класс исключения, которое создается при некорректном аргументе объекта
 */
public class InvalidFieldException extends Exception{

    public InvalidFieldException(String msg) {
        super(msg);
    }
}
