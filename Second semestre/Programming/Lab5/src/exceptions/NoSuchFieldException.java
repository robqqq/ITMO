package exceptions;

/**
 * Исключение при отсутствии поля, для получения сообщения у мессенджера
 */
public class NoSuchFieldException extends RuntimeException{

    public NoSuchFieldException(String msg){
        super(msg);
    }
}
