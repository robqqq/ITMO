package serverCommands;

import auth.Auth;
import collectionManager.CollectionManager;
import command.RequiringArg;
import dataManager.DataManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import exceptions.NoSuchIdException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Класс команды, которая удаляет элемент из коллекции по его id
 */
public class RemoveByIdCommand implements ServerCommand, RequiringArg<Integer>, RequiringAuth {
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private Locale locale;
    private int arg;
    private Auth auth;

    /**
     * @param collectionManager менеджер коллекции
     * @param dataManager менеджер данных
     */
    public RemoveByIdCommand(CollectionManager collectionManager, DataManager dataManager){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String execute() {
        try{
            dataManager.removeElement(arg, auth);
            collectionManager.removeElement(arg);
            return ResourceBundle.getBundle("messages", locale).getString("command_output.remove");
        } catch (NoSuchIdException e){
            return ResourceBundle.getBundle("messages", locale).getString("command_output.not_remove");
        }
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException {
        commandInvoker.setAuthToCommand(this);
        commandInvoker.setIntegerArgToCommand(this);
        commandInvoker.setLocaleToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
