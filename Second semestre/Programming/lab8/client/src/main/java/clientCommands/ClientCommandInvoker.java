package clientCommands;

import command.RequiringArg;
import exceptions.NoArgException;

public interface ClientCommandInvoker {

    void invokeCommand(ClientCommand command);

    void setStringArgToCommand(RequiringArg<String> command) throws NoArgException;

    void setArg(String arg);

    String getCommandOutput();
}
