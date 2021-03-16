package exceptions;

/**
 * Исключение для ошибки обработки данных
 */
public class DataException extends Exception{

    public DataException(String msg){
        super(msg);
    }
}
