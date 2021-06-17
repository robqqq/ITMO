package clientCommands;

import command.RequiringArg;
import exceptions.NoArgException;

import java.io.IOException;

public interface ClientCommandInvoker {

    void invokeCommand(ClientCommand command);

    void setStringArgToCommand(RequiringArg<String> command) throws NoArgException;

    void setArg(String arg);
}
