package serverCommands;

import networkMessages.Request;
import requests.RequestReceiver;
import requests.ServerRequestReceiver;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientCommandAnalyzer extends Thread implements CommandAnalyzer {
    private boolean exit;
    private final ServerCommandManager commandManager;
    private final DatagramChannel channel;
    private static final Logger logger = LoggerFactory.getLogger(ClientCommandAnalyzer.class);

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
                logger.error("serializing error", e);
            }
        }
        logger.info("server has stopped");
    }

    @Override
    public void stopReading() {
        exit = true;
    }
}
