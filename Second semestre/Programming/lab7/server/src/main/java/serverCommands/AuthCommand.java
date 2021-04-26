package serverCommands;

import auth.Auth;
import authManager.AuthManager;
import command.RequiringArg;
import exceptions.AuthException;
import exceptions.NoArgException;
import messages.Messenger;

public class AuthCommand implements ServerCommand, RequiringAuth, RequiringArg<Auth> {
    private Auth auth;
    private Auth arg;
    private final Messenger messenger;
    private final AuthManager authManager;

    public AuthCommand(Messenger messenger, AuthManager authManager){
        this.messenger = messenger;
        this.authManager = authManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public String execute() {
        authManager.removeOnlineUser(auth);
        if (authManager.checkAuth(arg) && !authManager.isOnline(arg)){
            authManager.addOnlineUser(arg);
            return messenger.getMsg("authOutput");
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
