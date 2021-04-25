package clientCommands;

import client.ClientApplication;
import command.RequiringArg;
import exceptions.NoArgException;
import exceptions.ScriptException;
import exceptions.ScriptRecursionException;
import input.ScriptInputManager;
import input.InputManager;
import messages.Messenger;
import output.OutputManager;

import java.io.*;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Класс команды, которая считывает и исполняет скрипт из указанного файла
 */
public class ExecuteScriptCommand implements ClientCommand, RequiringArg<String> {
    private final DatagramSocket socket;
    private final SocketAddress address;
    private final OutputManager outputManager;
    private final Messenger messenger;
    private final ClientApplication app;
    private String arg;

    public ExecuteScriptCommand(DatagramSocket socket, SocketAddress address, OutputManager outputManager,
                                Messenger messenger, ClientApplication app) {
        this.socket = socket;
        this.address = address;
        this.outputManager = outputManager;
        this.messenger = messenger;
        this.app = app;
    }

    @Override
    public String execute() {
        try{
            InputManager inputManager = new ScriptInputManager(
                    new BufferedReader(new InputStreamReader(new FileInputStream(arg))), messenger);
            ClientCommandManager commandManager = new ClientCommandManagerImpl(socket, address, inputManager, outputManager, messenger, app);
            if (commandManager.scriptIsUsed(arg)){
                commandManager.clearUsedScripts();
                throw new ScriptRecursionException();
            }
            commandManager.usedScriptAdd(arg);
            while (inputManager.ready()){
                try {
                    String inputString = inputManager.readCommand();
                    String[] input = inputString.split("\\s+", 2);
                    if (input.length < 2)
                        commandManager.executeCommand(input[0], "");
                    else
                        commandManager.executeCommand(input[0], input[1]);
                } catch (ScriptException e) {
                    outputManager.printErrorMsg(messenger.getMsg("script") + "\n");
                    return "";
                }
            }
            commandManager.usedScriptRemove(arg);
        } catch (IOException e){
            outputManager.printErrorMsg(messenger.getMsg("noFile") + "\n");
        }
        return messenger.getMsg("scriptOutput");
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(ClientCommandInvoker commandInvoker) throws NoArgException {
        commandInvoker.setStringArgToCommand(this);
        commandInvoker.invokeCommand(this);
    }
}
