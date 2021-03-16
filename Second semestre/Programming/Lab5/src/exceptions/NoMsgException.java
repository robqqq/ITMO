package exceptions;

/**
 * Исключение при отсутсвии необходимого сообщения у мессенджера
 */
public class NoMsgException extends RuntimeException{

    public NoMsgException(String msg){
        super(msg);
    }
}
