package main;

import commands.CommandManager;
import io.FileManager;

public class Main {
    public static void main(String[] args){
        FileManager fileManager = new FileManager();
        PersonManager personManager = new PersonManager(fileManager);
        CommandManager commandManager = new CommandManager(personManager);
        commandManager.start();
    }
}
