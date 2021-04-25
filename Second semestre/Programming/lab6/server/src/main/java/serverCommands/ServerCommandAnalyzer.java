package serverCommands;

import input.ServerConsoleCommandReader;
import inputManager.CommandReader;
import log.Log;
import messages.Messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerCommandAnalyzer extends Thread implements CommandAnalyzer {
    private boolean exit;
    private CommandReader commandReader;
    private Messenger messenger;
    private ServerCommandManager commandManager;

    public ServerCommandAnalyzer(ServerCommandManager commandManager, Messenger messenger){
        commandReader = new ServerConsoleCommandReader(new BufferedReader(new InputStreamReader(System.in)));
        this.commandManager = commandManager;
        this.messenger = messenger;
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                String inputString = commandReader.readCommand();
                if (inputString.equals("")) continue;
                commandManager.executeServerCommand(inputString);
            } catch (IOException e) {
                Log.log().error(messenger.getMsg("endOfInput"), e);
                stopReading();
            }
        }
    }

    @Override
    public void startReading() {
        start();
    }

    @Override
    public void stopReading() {
        exit = true;
    }
}
