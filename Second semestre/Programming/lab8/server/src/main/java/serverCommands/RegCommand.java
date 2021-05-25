package serverCommands;

import auth.Auth;
import authManager.AuthManager;
import command.RequiringArg;
import dataManager.DataManager;
import exceptions.*;

import java.util.Locale;

public class RegCommand implements ServerCommand, RequiringAuth, RequiringArg<Auth> {
    private Auth auth;
    private Auth arg;
    private Locale locale;
    private final AuthManager authManager;
    private final DataManager dataManager;

    public RegCommand(AuthManager authManager, DataManager dataManager){
        this.authManager = authManager;
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
        return "";
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
