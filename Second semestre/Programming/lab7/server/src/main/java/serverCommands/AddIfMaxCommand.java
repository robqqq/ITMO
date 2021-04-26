package serverCommands;

import auth.Auth;
import collectionManager.CollectionManager;
import dataManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import messages.Messenger;
import person.Person;
import person.RawPerson;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию, если его значение превышает значение
 * наибольшего элемента этой коллекции
 */
public class AddIfMaxCommand implements ServerCommand, RequiringObject, RequiringAuth{
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private final Messenger messenger;
    private RawPerson person;
    private Auth auth;

    public AddIfMaxCommand(CollectionManager collectionManager, DataManager dataManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        if (person.compareTo(collectionManager.getPersonStream().max(Person::compareTo).get().getRawPerson()) > 0) {
            collectionManager.addElement(dataManager.addElement(person, auth));
            return messenger.getMsg("addOutput");
        } else {
            return messenger.getMsg("notAddOutput");
        }
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
