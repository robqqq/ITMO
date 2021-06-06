package serverCommands;

import auth.Auth;
import collectionManager.CollectionManager;
import dataManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import person.RawPerson;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию
 */
public class AddCommand implements ServerCommand, RequiringObject, RequiringAuth {
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private Auth auth;
    private RawPerson person;
    private Locale locale;

    public AddCommand(CollectionManager collectionManager, DataManager dataManager){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String execute() {
        collectionManager.addElement(dataManager.addElement(person, auth));
        return ResourceBundle.getBundle("messages", locale).getString("command_output.add");
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setAuthToCommand(this);
        commandInvoker.setObjectToCommand(this);
        commandInvoker.setLocaleToCommand(this);
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
