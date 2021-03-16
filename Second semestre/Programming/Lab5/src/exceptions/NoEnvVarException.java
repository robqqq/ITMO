package exceptions;

/**
 * Исключение при отсутствии необходимой переменной окружения
 */
public class NoEnvVarException extends DataException{
    public NoEnvVarException(String msg) {
        super(msg);
    }
}
