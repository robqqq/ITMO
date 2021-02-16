package commands;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * Класс команды, которая считывает и исполняет скрипт из указанного файла
 */
public class ExecuteScriptCommand implements Command {
    private CommandManagerInterface commandManager;

    /**
     * Конструктор
     * @param commandManager
     */
    ExecuteScriptCommand(CommandManagerInterface commandManager){
        this.commandManager = commandManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            if (commandManager.scriptIsUsed(args[0])){
                System.out.println("Script execution stopped: script calls the recursion");
            } else {
                commandManager.usedScriptAdd(args[0]);
                try {
                    commandManager.readCommands(new BufferedReader(new InputStreamReader(new FileInputStream(args[0]))), "");
                } catch (FileNotFoundException e) {
                    System.out.printf("File %s does not exist\n", args[0]);
                }
            }
        } else {
            System.out.println("Invalid arguments: you must specify one argument");
        }
    }
}
