package serverCommands;

import auth.Auth;
import collectionManager.CollectionManager;
import dataManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Класс команды, которая очищает коллекцию
 */
public class ClearCommand implements ServerCommand, RequiringAuth{
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private Auth auth;
    private Locale locale;

    public ClearCommand(CollectionManager collectionManager, DataManager dataManager){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
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
        return ResourceBundle.getBundle("messages", locale).getString("command_output.clear");
    }
}
