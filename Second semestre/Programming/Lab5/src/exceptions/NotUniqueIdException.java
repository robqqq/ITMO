package exceptions;

/**
 * Исключение, которое создается при конфликте id объектов в коллекции
 */
public class NotUniqueIdException extends InvalidFieldException {

    public NotUniqueIdException(String msg) {
        super(msg);
    }
}
