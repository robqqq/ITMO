package serverCommands;

import input.ServerConsoleCommandReader;
import inputManager.CommandReader;
import messages.Messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerCommandAnalyzer implements CommandAnalyzer {
    private boolean exit;
    private final CommandReader commandReader;
    private final Messenger messenger;
    private final ServerCommandManager commandManager;
    private static final Logger logger = LoggerFactory.getLogger(ServerCommandAnalyzer.class);

    public ServerCommandAnalyzer(ServerCommandManager commandManager, Messenger messenger){
        commandReader = new ServerConsoleCommandReader(new BufferedReader(new InputStreamReader(System.in)));
        this.commandManager = commandManager;
        this.messenger = messenger;
    }

    @Override
    public void startReading() {
        while (!exit) {
            try {
                String inputString = commandReader.readCommand();
                if (inputString.equals("")) continue;
                commandManager.executeServerCommand(inputString);
            } catch (IOException e) {
                logger.error(messenger.getMsg("endOfInput"), e);
                commandManager.executeServerCommand("exit");
            }
        }
    }

    @Override
    public void stopReading() {
        exit = true;
    }
}
