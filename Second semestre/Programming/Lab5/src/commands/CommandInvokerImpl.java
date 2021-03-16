package commands;

import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import messages.Messenger;

/**
 * Реализация интерфейса CommandInvoker
 */
public class CommandInvokerImpl implements CommandInvoker{
    private String arg;
    private Messenger messenger;

    /**
     * @param messenger мессенджер
     */
    public CommandInvokerImpl(Messenger messenger){
        this.messenger = messenger;
    }

    @Override
    public void invokeCommand(Command command) {
        command.execute();
    }

    @Override
    public void setIntegerArg(RequiringArg<Integer> command) throws InvalidArgumentTypeException, NoArgException {
        if (!arg.equals("")) {
            try {
                command.setArg(Integer.parseInt(arg));
            } catch (NumberFormatException e) {
                throw new InvalidArgumentTypeException(messenger.getExceptionMsg("noInteger"));
            }
        } else {
            throw new NoArgException(messenger.getExceptionMsg("noArg"));
        }
    }

    @Override
    public void setStringArg(RequiringArg<String> command) throws NoArgException {
        if (!arg.equals("")){
            command.setArg(arg);
        } else {
            throw new NoArgException(messenger.getExceptionMsg("noArg"));
        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }
}
