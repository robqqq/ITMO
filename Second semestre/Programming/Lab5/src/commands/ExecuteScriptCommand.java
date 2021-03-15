package commands;

import application.Application;
import collectionManager.CollectionManager;
import exceptions.NoArgException;
import exceptions.NoSuchCommandException;
import exceptions.ScriptException;
import exceptions.ScriptRecursionException;
import input.ConsoleInputManager;
import input.InputManager;
import input.ScriptInputManager;
import messages.Messenger;
import output.OutputManager;

import javax.naming.directory.SchemaViolationException;
import java.io.*;
import java.util.NoSuchElementException;

/**
 * Класс команды, которая считывает и исполняет скрипт из указанного файла
 */
public class ExecuteScriptCommand implements Command, RequiringArg<String> {
    private OutputManager outputManager;
    private CollectionManager collectionManager;
    private Application app;
    private Messenger messenger;
    private String arg;

    public ExecuteScriptCommand(Messenger messenger, OutputManager outputManager, CollectionManager collectionManager,
                                Application app){
        this.messenger = messenger;
        this.outputManager = outputManager;
        this.collectionManager = collectionManager;
        this.app = app;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        try{
            InputManager inputManager = new ScriptInputManager(
                    new BufferedReader(new InputStreamReader(new FileInputStream(arg))), messenger, outputManager);
            CommandManager commandManager = new CommandManagerImpl(collectionManager, app, messenger, outputManager, inputManager);
            if (commandManager.scriptIsUsed(arg)){
                commandManager.clearUsedScripts();
                throw new ScriptRecursionException(messenger.getExceptionMsg("scriptRecursion"));
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
                } catch (NoSuchCommandException | ScriptException e) {
                    outputManager.printErrorMsg(messenger.getExceptionMsg("script") + "\n");
                    return;
                }
            }
            commandManager.usedScriptRemove(arg);
        } catch (IOException e){
            outputManager.printErrorMsg(messenger.getExceptionMsg("noFile") + "\n");
        }
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void acceptInvoker(CommandInvoker commandInvoker) throws NoArgException {
        commandInvoker.setStringArg(this);
        commandInvoker.invokeCommand(this);
    }
}
