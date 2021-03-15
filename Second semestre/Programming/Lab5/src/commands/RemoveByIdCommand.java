package commands;

import collectionManager.CollectionManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import messages.Messenger;
import output.OutputManager;

/**
 * Класс команды, которая удаляет элемент из коллекции по его id
 */
public class RemoveByIdCommand implements Command, RequiringArg<Integer>{
    private CollectionManager collectionManager;
    Messenger messenger;
    OutputManager outputManager;
    private int arg;

    public RemoveByIdCommand(CollectionManager collectionManager, Messenger messenger, OutputManager outputManager){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.outputManager = outputManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        if(!collectionManager.removeElement(arg)){
            outputManager.printErrorMsg(messenger.getExceptionMsg("noSuchId") + "\n");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(CommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException {
        commandInvoker.setIntegerArg(this);
        commandInvoker.invokeCommand(this);
    }
}
