package serverCommands;

import auth.Auth;
import authManager.AuthManager;
import command.RequiringArg;
import dataManager.DataManager;
import exceptions.*;
import messages.Messenger;

public class RegCommand implements ServerCommand, RequiringAuth, RequiringArg<Auth> {
    private Auth auth;
    private Auth arg;
    private final Messenger messenger;
    private final AuthManager authManager;
    private final DataManager dataManager;

    public RegCommand(Messenger messenger, AuthManager authManager, DataManager dataManager){
        this.messenger = messenger;
        this.authManager = authManager;
        this.dataManager = dataManager;
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public String execute() {
        authManager.removeOnlineUser(auth);
        try{
            dataManager.addUser(arg);
            authManager.addUser(arg);
            authManager.addOnlineUser(arg);
        } catch (DBException e){
            throw new AuthException();
        }
        return messenger.getMsg("regOutput");
    }

    /**
     * Метод, который указывает, как запускать эту команду
     *
     * @param commandInvoker объект CommandInvoker
     */
    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setAuthToCommand(this);
        commandInvoker.setAuthArgToCommand(this);
        commandInvoker.invokeCommand(this);
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
}
