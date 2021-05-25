package serverCommands;

import auth.Auth;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import networkMessages.RequestType;
import person.RawPerson;

import java.util.Locale;

public interface ServerCommandInvoker {

    void invokeCommand(ServerCommand command);

    void setIntegerArgToCommand(RequiringArg<Integer> command) throws InvalidArgumentTypeException, NoArgException;

    void setStringArgToCommand(RequiringArg<String> command) throws NoArgException;

    void setAuthToCommand(RequiringAuth command) throws NoArgException;

    void setAuthArgToCommand(RequiringArg<Auth> command);

    void setObjectToCommand(RequiringObject command) throws NeedObjectException;

    void setLocaleToCommand(ServerCommand command);

    void setArg(String arg);

    void setAuth(Auth auth);

    void setObject(RawPerson person);

    void setType(RequestType type);

    void setLocale(Locale locale);

    String getCommandOutput();
}
