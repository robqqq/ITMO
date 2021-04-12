package serverCommands;

import collectionManager.CollectionManager;
import collectionManager.PersonIdManager;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import messages.Messenger;
import person.Person;
import person.RawPerson;

/**
 * Класс команды, которая обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand implements ServerCommand, RequiringArg<Integer>, RequiringObject {
    private final CollectionManager collectionManager;
    private final Messenger messenger;
    private int arg;
    private RawPerson person;

    /**
     * @param collectionManager менеджер коллекции
     * @param messenger мессенджер
     */
    public UpdateCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        if (!PersonIdManager.INSTANCE.idIsFree(arg)){
            PersonIdManager.INSTANCE.removeId(arg);
            Person oldPerson = collectionManager.getPersonStream().filter(person -> person.getId() == arg).findAny().get();
            Person newPerson = new Person(oldPerson.getId(), person, oldPerson.getCreationDate());
            collectionManager.removeElement(arg);
            collectionManager.addElement(person);
            return messenger.getMsg("updateOutput");
        } else {
            return messenger.getMsg("notUpdateOutput");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setIntegerArgToCommand(this);
        commandInvoker.setObjectToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setObject(RawPerson person) {
        this.person = person;
    }
}
