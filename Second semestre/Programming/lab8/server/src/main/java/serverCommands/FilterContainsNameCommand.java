package serverCommands;

import collectionManager.CollectionManager;
import command.RequiringArg;
import exceptions.NoArgException;

import java.util.Locale;

/**
 * Класс команды, которая выводит элементы, значение поля name которых содержит заданную подстроку
 */
public class FilterContainsNameCommand implements ServerCommand, RequiringArg<String> {
    private final CollectionManager collectionManager;
    private String arg;
    private Locale locale;

    /**
     * @param collectionManager менеджер коллекции
     */
    public FilterContainsNameCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        collectionManager.getPersonStream()
                .filter(person -> person.getName().contains(arg))
                .forEachOrdered(person -> stringBuilder.append(person).append("\n"));
        return stringBuilder.toString();
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException {
        commandInvoker.setStringArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
