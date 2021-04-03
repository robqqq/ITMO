package clientCommands;

import exceptions.NoArgException;

/**
 * Общий интерфейс всех классов команд
 */
public interface ClientCommand {

    /**
     * Метод, который запускает команду
     */
    String execute();

    /**
     * Метод, который указывает, как запускать эту команду
     * @param commandInvoker объект CommandInvoker
     */
    default void acceptInvoker(ClientCommandInvoker commandInvoker) throws NoArgException{
        commandInvoker.invokeCommand(this);
    }
}
