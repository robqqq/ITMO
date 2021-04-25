package clientCommands;

import command.RequiringArg;
import exceptions.NoArgException;

public class ClientCommandInvokerImpl implements ClientCommandInvoker {
    private String arg;
    private String output;

    @Override
    public void invokeCommand(ClientCommand command) {
        output = command.execute();
        arg = null;
    }

    @Override
    public void setStringArgToCommand(RequiringArg<String> command) throws NoArgException {
        if (arg != null && !arg.equals("")){
            command.setArg(arg);
        } else {
            throw new NoArgException();
        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public String getCommandOutput(){
        return output;
    }
}
