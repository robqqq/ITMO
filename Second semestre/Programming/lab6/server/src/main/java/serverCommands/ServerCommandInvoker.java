package serverCommands;

import command.RequiringArg;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import networkMessages.RequestType;
import person.RawPerson;

public interface ServerCommandInvoker {

    void invokeCommand(ServerCommand command);

    void setIntegerArgToCommand(RequiringArg<Integer> command) throws InvalidArgumentTypeException, NoArgException;

    void setStringArgToCommand(RequiringArg<String> command) throws NoArgException;

    void setObjectToCommand(RequiringObject command) throws NeedObjectException;

    void setArg(String arg);

    void setObject(RawPerson person);

    void setType(RequestType type);

    String getCommandOutput();
}
