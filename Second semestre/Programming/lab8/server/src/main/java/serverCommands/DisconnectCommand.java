package serverCommands;

import auth.Auth;
import authManager.AuthManager;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;

import java.util.Locale;

public class DisconnectCommand implements ServerCommand, RequiringAuth{
    private Auth auth;
    private AuthManager authManager;
    private Locale locale;

    public DisconnectCommand(AuthManager authManager){
        this.authManager = authManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public String execute() {
        authManager.removeOnlineUser(auth);
        return "";
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
     * Метод, который указывает, как запускать эту команду
     *
     * @param commandInvoker объект CommandInvoker
     */
    @Override
    public void acceptInvoker(ServerCommandInvoker commandInvoker) throws NoArgException, InvalidArgumentTypeException, NeedObjectException {
        commandInvoker.setAuthToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
