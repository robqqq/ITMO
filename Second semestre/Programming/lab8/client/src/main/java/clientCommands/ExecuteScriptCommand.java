package clientCommands;

import client.ClientApplication;
import command.RequiringArg;
import exceptions.NoArgException;
import exceptions.ScriptException;
import exceptions.ScriptRecursionException;
import input.InputManager;
import input.ScriptInputManager;

import java.io.*;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Класс команды, которая считывает и исполняет скрипт из указанного файла
 */
public class ExecuteScriptCommand implements ClientCommand, RequiringArg<String> {
    private final ClientCommandManager commandManager;
    private String arg;

    public ExecuteScriptCommand(ClientCommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute() {
        try {
            commandManager.executeScript(new File(arg));
        } catch (IOException ioException) {
            throw new ScriptException();
        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ClientCommandInvoker commandInvoker) throws NoArgException{
        commandInvoker.setStringArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
