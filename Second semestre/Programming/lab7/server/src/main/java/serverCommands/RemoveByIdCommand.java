package serverCommands;

import auth.Auth;
import collectionManager.CollectionManager;
import command.RequiringArg;
import dataManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import exceptions.NoSuchIdException;
import messages.Messenger;

/**
 * Класс команды, которая удаляет элемент из коллекции по его id
 */
public class RemoveByIdCommand implements ServerCommand, RequiringArg<Integer>, RequiringAuth {
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private final Messenger messenger;
    private int arg;
    private Auth auth;

    /**
     * @param collectionManager менеджер коллекции
     * @param dataManager менеджер данных
     * @param messenger мессенджер
     */
    public RemoveByIdCommand(CollectionManager collectionManager, DataManager dataManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        try{
            dataManager.removeElement(arg, auth);
            collectionManager.removeElement(arg);
            return messenger.getMsg("removeOutput");
        } catch (NoSuchIdException e){
            return messenger.getMsg("notRemoveOutput");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException {
        commandInvoker.setAuthToCommand(this);
        commandInvoker.setIntegerArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
