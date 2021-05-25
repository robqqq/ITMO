package serverCommands;

import auth.Auth;
import authManager.AuthManager;
import command.RequiringArg;
import exceptions.AuthException;
import exceptions.NoArgException;

import java.util.Locale;

public class AuthCommand implements ServerCommand, RequiringAuth, RequiringArg<Auth> {
    private Auth auth;
    private Auth arg;
    private final AuthManager authManager;
    private Locale locale;

    public AuthCommand(AuthManager authManager){
        this.authManager = authManager;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public String execute() {
        authManager.removeOnlineUser(auth);
        if (authManager.checkAuth(arg) && !authManager.isOnline(arg)){
            authManager.addOnlineUser(arg);
            return "";
        } else {
            throw new AuthException();
        }
    }

    /**
     * Метод, который указывает, как запускать эту команду
     *
     * @param commandInvoker объект CommandInvoker
     */
    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException {
        commandInvoker.setAuthToCommand(this);
        commandInvoker.setAuthArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
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
