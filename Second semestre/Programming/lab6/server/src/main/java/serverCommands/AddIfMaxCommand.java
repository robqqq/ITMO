package serverCommands;

import collectionManager.CollectionManager;
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
public class AddIfMaxCommand implements ServerCommand, RequiringObject{
    private CollectionManager collectionManager;
    private Messenger messenger;
    private RawPerson person;

    public AddIfMaxCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        if (person.compareTo(collectionManager.getPersonStream().max(Person::compareTo).get().getRawPerson()) > 0) {
            collectionManager.addElement(person);
            return messenger.getMsg("addOutput");
        } else {
            return messenger.getMsg("notAddOutput");
        }
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
