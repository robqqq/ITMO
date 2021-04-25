package serverCommands;

import auth.Auth;
import authManager.AuthManager;
import command.RequiringArg;
import exceptions.AuthException;
import exceptions.NoArgException;
import messages.Messenger;

public class AuthCommand implements ServerCommand, RequiringArg<Auth> {
    private Auth arg;
    private final Messenger messenger;
    private final AuthManager authManager;

    public AuthCommand(Messenger messenger, AuthManager authManager){
        this.messenger = messenger;
        this.authManager = authManager;
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
        if (authManager.checkAuth(arg)){
            return messenger.getMsg("authOutput"); //TODO: добавить аузсОутпут в мессенджер
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
        commandInvoker.setAuthArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
