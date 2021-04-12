package server;

import application.Application;
import collectionManager.CollectionManager;
import collectionManager.PersonCollectionManager;
import connection.ServerConnectionManager;
import connection.ServerConnectionManagerImpl;
import dataManager.*;
import exceptions.*;
import messages.Messenger;
import messages.MessengerImpl;
import serverCommands.*;
import serverCommands.ClientCommandAnalyzer;
import serverCommands.CommandAnalyzer;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServerApplication implements Application {
    private final int port;
    private CommandAnalyzer clientCommandAnalyzer;
    private CommandAnalyzer serverCommandAnalyzer;
    private ServerConnectionManager connectionManager;
    private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    public ServerApplication(int port){
        this.port = port;
    }

    @Override
    public void start(){
        String fileName = System.getenv("Lab6");
        Messenger messenger = new MessengerImpl();
        DataReader dataReader = new XMLPersonReader(fileName);
        DataWriter dataWriter = new XMLPersonWriter(fileName);
        DataManager dataManager = new DataManagerImpl(dataReader, dataWriter);
        CollectionManager collectionManager = new PersonCollectionManager(dataManager);
        connectionManager = new ServerConnectionManagerImpl();
        ServerCommandManager commandManager = new ServerCommandManagerImpl(collectionManager, this, messenger);
        serverCommandAnalyzer = new ServerCommandAnalyzer(commandManager, messenger);
        try {
            DatagramChannel channel = connectionManager.openConnection(port);
            clientCommandAnalyzer = new ClientCommandAnalyzer(commandManager, channel);
        } catch (IOException e) {
            logger.error("opening connection error", e);
            return;
        }
        try {
            collectionManager.loadPersons();
        } catch (InvalidFieldException | NoDataException | BrokenDataException e) {
            logger.error("File is broken", e);
        } catch (NoEnvVarException e){
            logger.error("environmental variable Lab6 doesn't exist, application stopped", e);
            return;
        }
        logger.info("server has started");
        clientCommandAnalyzer.startReading();
        serverCommandAnalyzer.startReading();
    }

    @Override
    public void exit(){
        try {
            connectionManager.closeConnection();
        } catch (IOException e) {
            logger.error("connection closing error", e);
        }
        clientCommandAnalyzer.stopReading();
        serverCommandAnalyzer.stopReading();
    }
}