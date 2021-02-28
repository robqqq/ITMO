package cleint;

import commands.CommandManagerInterface;
import exceptions.InvalidArgumentException;
import main.ObjectManager;

import java.io.*;
import java.util.logging.Level;

import static log.Log.logger;

public class ClientManager implements ClientManagerInterface{
    private CommandManagerInterface commandManager;
    private BufferedReader reader;
    private PrintStream printStream;
    private String[] input;
    private boolean exit;
    private boolean script;

    public ClientManager(CommandManagerInterface commandManager){
        this.commandManager = commandManager;
        reader = new BufferedReader(new InputStreamReader(System.in));
        printStream = System.out;
        exit = false;
    }

    public void start(ObjectManager objectManager){
        logger.log(Level.INFO, "start program");
        try {
            objectManager.readFromFile();
        } catch (InvalidArgumentException e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            System.err.println("File is broken: " + e.getMessage() + ". Program started with empty collection");
            commandManager.executeCommand("clear".split(" "), this);
        }
        readCommands();
    }

    public void readCommands(){
        try {
            while (!exit && (reader.ready() == script)) {
                if (!script) {
                    printStream.print("> ");
                }
                input = reader.readLine().trim().split("\\s+");
                if (input[0].length() != 0) {
                    commandManager.executeCommand(input, this);
                }
            }
        }catch (IOException e){
            logger.log(Level.WARNING, e.toString(), e);
            printStream.println(e.getMessage());
        }
    }

    public void readScript(FileInputStream fileInputStream){
        reader = new BufferedReader(new InputStreamReader(fileInputStream));
        script = true;
        readCommands();
        script = false;
        commandManager.clearUsedScripts();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String askValue(String msg){
        String value = null;
        try {
            if (!script) {
                printStream.print(msg + ": ");
            }
            if (!script | reader.ready()) {
                value = reader.readLine().trim();
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
        }
        return value;
    }

    public void exit(){
        exit = true;
    }

    public boolean isScript(){
        return script;
    }
}
