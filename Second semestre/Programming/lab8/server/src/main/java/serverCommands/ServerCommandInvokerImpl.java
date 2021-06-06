package serverCommands;

import auth.Auth;
import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import networkMessages.RequestType;
import person.RawPerson;

import java.util.Locale;

public class ServerCommandInvokerImpl implements ServerCommandInvoker{
    private String arg;
    private RawPerson person;
    private RequestType type;
    private String output;
    private Auth auth;
    private Locale locale;

    @Override
    public void setAuthArgToCommand(RequiringArg<Auth> command) {
        command.setArg(Auth.parse(arg));
    }

    @Override
    public void invokeCommand(ServerCommand command) {
        output = command.execute();
    }

    @Override
    public void setIntegerArgToCommand(RequiringArg<Integer> command) throws InvalidArgumentTypeException, NoArgException {
        if (type == RequestType.ARG_REQUEST || type == RequestType.ARG_OBJECT_REQUEST){
            try{
                command.setArg(Integer.parseInt(arg));
            } catch (NumberFormatException e){
                throw new InvalidArgumentTypeException();
            }
        } else {
            throw new NoArgException();
        }
    }

    @Override
    public void setStringArgToCommand(RequiringArg<String> command) throws NoArgException {
        if (type == RequestType.ARG_REQUEST || type == RequestType.ARG_OBJECT_REQUEST){
            command.setArg(arg);
        } else {
            throw new NoArgException();
        }
    }

    @Override
    public void setAuthToCommand(RequiringAuth command) throws NoArgException{
        command.setAuth(auth);
    }

    @Override
    public void setObjectToCommand(RequiringObject command) throws NeedObjectException {
        if (type == RequestType.OBJECT_REQUEST || type == RequestType.ARG_OBJECT_REQUEST){
            command.setObject(person);
        } else {
            throw new NeedObjectException();
        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    @Override
    public void setObject(RawPerson person) {
        this.person = person;
    }

    @Override
    public void setType(RequestType type) {
        this.type = type;
    }

    @Override
    public String getCommandOutput() {
        return this.output;
    }

    @Override
    public void setLocaleToCommand(ServerCommand command) {
        command.setLocale(locale);
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}