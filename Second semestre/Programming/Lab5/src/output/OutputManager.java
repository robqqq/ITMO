package output;

/**
 * Интерфейс менеджера вывода
 */
public interface OutputManager {

    /**
     * Вывести сообщение
     * @param msg сообщение
     */
    void printMsg(String msg);

    /**
     * Вывести сообщение ошибки
     * @param msg сообщение
     */
    void printErrorMsg(String msg);
}
