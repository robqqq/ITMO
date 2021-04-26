package serverCommands;

import auth.Auth;
import collectionManager.CollectionManager;
import dataManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import messages.Messenger;

/**
 * Класс команды, которая очищает коллекцию
 */
public class ClearCommand implements ServerCommand, RequiringAuth{
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private final Messenger messenger;
    private Auth auth;

    public ClearCommand(CollectionManager collectionManager, DataManager dataManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.messenger = messenger;
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setAuthToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public String execute() {
        dataManager.clearElements(auth);
        collectionManager.clear();
        collectionManager.setCollection(dataManager.readElements());
        return messenger.getMsg("clearOutput");
    }
}
