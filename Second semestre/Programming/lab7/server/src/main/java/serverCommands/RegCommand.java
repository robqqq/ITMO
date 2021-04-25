package serverCommands;

import auth.Auth;
import authManager.AuthManager;
import command.RequiringArg;
import dbManager.DataManager;
import exceptions.*;
import messages.Messenger;

import java.sql.SQLException;

public class RegCommand implements ServerCommand, RequiringArg<Auth> {
    private Auth arg;
    private final Messenger messenger;
    private final AuthManager authManager;
    private final DataManager dataManager;

    public RegCommand(Messenger messenger, AuthManager authManager, DataManager dataManager){
        this.messenger = messenger;
        this.authManager = authManager;
        this.dataManager = dataManager;
    }
    /**
     * Метод, который устанавливает аргумент
     *
     * @param arg аргумент
     */
    @Override
    public void setArg(Auth arg) {
        this.arg = arg;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public String execute() {
        try{
            dataManager.addUser(arg);
            authManager.addUser(arg);
        } catch (DBException e){
            throw new AuthException();
        }
        return messenger.getMsg("regOutput"); //TODO: add reg output to messenger
    }

    /**
     * Метод, который указывает, как запускать эту команду
     *
     * @param commandInvoker объект CommandInvoker
     */
    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setAuthArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
