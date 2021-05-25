package serverCommands;

import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;

import java.util.Locale;

public interface ServerCommand {

    /**
     * Метод, который запускает команду
     */
    String execute();

    void setLocale(Locale locale);

    /**
     * Метод, который указывает, как запускать эту команду
     * @param commandInvoker объект CommandInvoker
     */
    default void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setLocaleToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
