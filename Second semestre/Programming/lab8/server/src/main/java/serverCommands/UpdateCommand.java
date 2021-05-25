package serverCommands;

import auth.Auth;
import collectionManager.CollectionManager;
import command.RequiringArg;
import dataManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import exceptions.NoSuchIdException;
import person.Person;
import person.RawPerson;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Класс команды, которая обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand implements ServerCommand, RequiringArg<Integer>, RequiringObject, RequiringAuth {
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private Locale locale;
    private int arg;
    private RawPerson person;
    private Auth auth;

    /**
     * @param collectionManager менеджер коллекции
     * @param dataManager менеджер данных
     */
    public UpdateCommand(CollectionManager collectionManager, DataManager dataManager){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
    }

    @Override
    public String execute() {
            try{
                Person newPerson = dataManager.updateElement(person, arg, auth);
                collectionManager.removeElement(arg);
                collectionManager.addElement(newPerson);
                return ResourceBundle.getBundle("messages", locale).getString("command_output.update");
            } catch (NoSuchIdException e){
                return ResourceBundle.getBundle("messages", locale).getString("command_output.not_update");
            }
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
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
