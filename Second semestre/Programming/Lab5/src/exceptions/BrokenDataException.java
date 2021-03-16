package exceptions;

/**
 * Исключение для ошибки в структуре данных
 */
public class BrokenDataException extends DataException{
    public BrokenDataException(String msg) {
        super(msg);
    }
}
