package serverCommands;

import collectionManager.CollectionManager;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import messages.Messenger;

/**
 * Класс команды, которая удаляет элемент из коллекции по его id
 */
public class RemoveByIdCommand implements ServerCommand, RequiringArg<Integer> {
    private CollectionManager collectionManager;
    private Messenger messenger;
    private int arg;

    /**
     * @param collectionManager менеджер коллекции
     * @param messenger мессенджер
     */
    public RemoveByIdCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        if(collectionManager.removeElement(arg)){
            return messenger.getMsg("removeOutput");
        } else {
            return messenger.getMsg("notRemoveOutput");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException {
        commandInvoker.setIntegerArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
