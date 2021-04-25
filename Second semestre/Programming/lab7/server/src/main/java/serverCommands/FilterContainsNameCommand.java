package serverCommands;

import collectionManager.CollectionManager;
import command.RequiringArg;
import exceptions.NoArgException;
import messages.Messenger;

/**
 * Класс команды, которая выводит элементы, значение поля name которых содержит заданную подстроку
 */
public class FilterContainsNameCommand implements ServerCommand, RequiringArg<String> {
    private final CollectionManager collectionManager;
    private final Messenger messenger;
    private String arg;

    /**
     * @param collectionManager менеджер коллекции
     * @param messenger мессенджер
     */
    public FilterContainsNameCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messenger.getMsg("filterContainsNameOutput"))
                .append(":\n");
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
