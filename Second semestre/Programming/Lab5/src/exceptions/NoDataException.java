package exceptions;

/**
 * Исключение при отсутствии данных коллекции
 */
public class NoDataException extends DataException{
    public NoDataException(String msg) {
        super(msg);
    }
}
