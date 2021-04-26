package serverCommands;

import auth.Auth;
import collectionManager.CollectionManager;
import dataManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import messages.Messenger;
import person.RawPerson;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию
 */
public class AddCommand implements ServerCommand, RequiringObject, RequiringAuth {
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private final Messenger messenger;
    private Auth auth;
    private RawPerson person;

    public AddCommand(CollectionManager collectionManager, DataManager dataManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        collectionManager.addElement(dataManager.addElement(person, auth));
        return messenger.getMsg("addOutput");
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setAuthToCommand(this);
        commandInvoker.setObjectToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setObject(RawPerson person) {
        this.person = person;
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
