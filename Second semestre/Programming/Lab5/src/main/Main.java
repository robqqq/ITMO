package main;

import cleint.ClientManager;
import commands.CommandManager;
import fileManager.FileManager;

public class Main {
    public static void main(String[] args){
        FileManager fileManager = new FileManager();
        PersonManager personManager = new PersonManager(fileManager);
        CommandManager commandManager = new CommandManager(personManager);
        ClientManager clientManager = new ClientManager(commandManager);
        clientManager.start(personManager);
    }
}
