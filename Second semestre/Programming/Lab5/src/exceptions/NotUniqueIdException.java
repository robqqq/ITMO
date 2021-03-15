package exceptions;

/**
 * Класс исключения, которое создается при конфликте id объектов в коллекции
 */
public class NotUniqueIdException extends InvalidFieldException {

    /**
     * Конструктор
     */
    public NotUniqueIdException(String msg) {
        super(msg);
    }
}
