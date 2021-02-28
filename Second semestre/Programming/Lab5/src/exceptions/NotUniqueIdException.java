package exceptions;

/**
 * Класс исключения, которое создается при конфликте id объектов в коллекции
 */
public class NotUniqueIdException extends InvalidArgumentException{

    /**
     * Конструктор
     */
    public NotUniqueIdException() {
        super("Collection has not unique id");
    }
}
