package serverCommands;

import input.ServerConsoleCommandReader;
import inputManager.CommandReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerCommandHandler extends Thread implements CommandHandler{
    private boolean exit;
    private final CommandReader commandReader;
    private final ServerCommandManager commandManager;
    private static final Logger logger = LoggerFactory.getLogger(ServerCommandHandler.class);

    public ServerCommandHandler(ServerCommandManager commandManager){
        commandReader = new ServerConsoleCommandReader(new BufferedReader(new InputStreamReader(System.in)));
        this.commandManager = commandManager;
    }

    public void startReading() {
        start();
    }

    public void stopReading() {
        exit = true;
    }

    public void run() {
        while (!exit) {
            try {
                String inputString = commandReader.readCommand();
                if (inputString.equals("")) continue;
                commandManager.executeServerCommand(inputString);
            } catch (IOException e) {
                logger.error("End of input", e);
                commandManager.executeServerCommand("exit");
            }
        }
    }
}
