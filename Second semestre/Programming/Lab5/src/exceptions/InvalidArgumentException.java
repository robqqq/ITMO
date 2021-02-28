package exceptions;

/**
 * Класс исключения, которое создается при некорректном аргументе объекта
 */
public class InvalidArgumentException extends Exception{

    /**
     * Конструктор
     * @param message
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
