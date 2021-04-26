package serverCommands;

import auth.Auth;
import collectionManager.CollectionManager;
import command.RequiringArg;
import dataManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import exceptions.NoSuchIdException;
import messages.Messenger;
import person.Person;
import person.RawPerson;

/**
 * Класс команды, которая обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand implements ServerCommand, RequiringArg<Integer>, RequiringObject, RequiringAuth {
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private final Messenger messenger;
    private int arg;
    private RawPerson person;
    private Auth auth;

    /**
     * @param collectionManager менеджер коллекции
     * @param dataManager менеджер данных
     * @param messenger мессенджер
     */
    public UpdateCommand(CollectionManager collectionManager, DataManager dataManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
            try{
                Person newPerson = dataManager.updateElement(person, arg, auth);
                collectionManager.removeElement(arg);
                collectionManager.addElement(newPerson);
                return messenger.getMsg("updateOutput");
            } catch (NoSuchIdException e){
                return messenger.getMsg("notUpdateOutput");
            }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setAuthToCommand(this);
        commandInvoker.setIntegerArgToCommand(this);
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
