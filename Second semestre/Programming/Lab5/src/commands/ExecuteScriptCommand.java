package commands;

import cleint.ClientManagerInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Класс команды, которая считывает и исполняет скрипт из указанного файла
 */
public class ExecuteScriptCommand implements Command {
    private CommandManagerInterface commandManager;
    private final String arguments;
    private final String description;

    ExecuteScriptCommand(CommandManagerInterface commandManager){
        this.commandManager = commandManager;
        arguments = "file_name";
        description = "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, " +
                "в котором их вводит пользователь в интерактивном режиме";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        if (args.length == 1){
            if (commandManager.scriptIsUsed(args[0])){
                System.out.println("Script execution stopped: script calls the recursion");
            } else {
                commandManager.usedScriptAdd(args[0]);
                try {
                    clientManager.readScript(new FileInputStream(args[0]));
                } catch (FileNotFoundException e) {
                    System.out.printf("File %s does not exist\n", args[0]);
                }
            }
        } else {
            System.out.println("Invalid arguments: you must specify one argument");
        }
    }

    @Override
    public String getHelp() {
        return String.format("%s : %s", arguments, description);
    }
}
