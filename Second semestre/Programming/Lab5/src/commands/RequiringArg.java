package commands;

/**
 * Интерфейс для команд, которым необходим аргумент
 * @param <T> тип данных аргумента
 */
public interface RequiringArg<T> {

    /**
     * Метод, который устанавливает аргумент
     * @param arg аргумент
     */
    void setArg(T arg);
}
