package serverCommands;

import log.Log;
import networkMessages.Request;
import requests.RequestReceiver;
import requests.ServerRequestReceiver;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class ClientCommandAnalyzer extends Thread implements CommandAnalyzer {
    private boolean exit;
    private ServerCommandManager commandManager;
    private DatagramChannel channel;

    public ClientCommandAnalyzer(ServerCommandManager commandManager, DatagramChannel channel){
        this.commandManager = commandManager;
        this.channel = channel;
    }

    @Override
    public void startReading(){
        start();
    }

    @Override
    public void run() {
        while (!exit){
            try {
                RequestReceiver requestReceiver = new ServerRequestReceiver(channel);
                Request request = requestReceiver.receiveRequest();
                commandManager.executeClientCommand(request.getType(), request.getCommand(), request.getArg(),
                        request.getObject(), requestReceiver.getAddress(), channel);
            } catch (IOException e) {
                stopReading();
            } catch (ClassNotFoundException e) {
                Log.log().error("serializing error", e);
            }
        }
        Log.log().info("server has stopped");
    }

    @Override
    public void stopReading() {
        exit = true;
    }
}
