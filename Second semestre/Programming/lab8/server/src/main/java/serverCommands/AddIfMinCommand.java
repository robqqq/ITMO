package serverCommands;

import auth.Auth;
import collectionManager.CollectionManager;
import dataManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import person.Person;
import person.RawPerson;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего
 * элемента этой коллекции
 */
public class AddIfMinCommand implements ServerCommand, RequiringObject, RequiringAuth{
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private RawPerson person;
    private Auth auth;
    private Locale locale;

    public AddIfMinCommand(CollectionManager collectionManager, DataManager dataManager){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String execute() {
        if (person.compareTo(collectionManager.getPersonStream().min(Person::compareTo).get().getRawPerson()) < 0) {
            collectionManager.addElement(dataManager.addElement(person, auth));
            return ResourceBundle.getBundle("messages", locale).getString("command_output.add");
        } else {
            return ResourceBundle.getBundle("messages", locale).getString("command_output.not_add");
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
