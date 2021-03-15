package commands;

import collectionManager.CollectionManager;
import exceptions.NoArgException;
import messages.Messenger;
import output.OutputManager;

/**
 * Класс команды, которая выводит элементы, значение поля name которых содержит заданную подстроку
 */
public class FilterContainsNameCommand implements Command, RequiringArg<String>{
    private CollectionManager collectionManager;
    private Messenger messenger;
    private OutputManager outputManager;
    private String arg;

    FilterContainsNameCommand(CollectionManager collectionManager, Messenger messenger, OutputManager outputManager){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.outputManager = outputManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        collectionManager.getPersonStream()
                .filter(person -> person.getName().contains(arg))
                .forEachOrdered(person -> outputManager.printMsg(messenger.getPersonString(person) + "\n"));
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(CommandInvoker commandInvoker) throws NoArgException {
        commandInvoker.setStringArg(this);
        commandInvoker.invokeCommand(this);
    }
}
