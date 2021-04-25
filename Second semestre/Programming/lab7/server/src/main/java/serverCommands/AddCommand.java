package serverCommands;

import collectionManager.CollectionManager;
import dbManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import messages.Messenger;
import person.RawPerson;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию
 */
public class AddCommand implements ServerCommand, RequiringObject {
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private final Messenger messenger;
    private RawPerson person;

    public AddCommand(CollectionManager collectionManager, DataManager dataManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        collectionManager.addElement(dataManager.addElement(person));
        return messenger.getMsg("addOutput");
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setObjectToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setObject(RawPerson person) {
        this.person = person;
    }
}
