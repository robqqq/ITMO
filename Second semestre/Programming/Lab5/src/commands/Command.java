package commands;

import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;

/**
 * Общий интерфейс всех классов команд
 */
public interface Command {

    /**
     * Метод, который запускает команду
     */
    void execute();

    /**
     * Метод, который указывает, как запускать эту команду
     * @param commandInvoker объект CommandInvoker
     */
    default void acceptInvoker(CommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException {
        commandInvoker.invokeCommand(this);
    }
}
