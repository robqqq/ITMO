package server;

import application.Application;
import authManager.AuthManager;
import authManager.AuthManagerImpl;
import collectionManager.CollectionManager;
import collectionManager.PersonCollectionManager;
import connection.ServerConnectionManager;
import connection.ServerConnectionManagerImpl;
import dbManager.*;
import messages.Messenger;
import messages.MessengerImpl;
import serverCommands.*;
import serverCommands.ClientCommandAnalyzer;
import serverCommands.CommandAnalyzer;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServerApplication implements Application {
    private final int port;
    private CommandAnalyzer clientCommandAnalyzer;
    private CommandAnalyzer serverCommandAnalyzer;
    private ServerConnectionManager connectionManager;
    private final String dbUsername;
    private final String dbPassword;
    private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    public ServerApplication(int port, String dbUsername, String dbPassword){
        this.port = port;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    @Override
    public void start(){
        Messenger messenger = new MessengerImpl();
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        DataManager dataManager = null;
        try {
            dataManager = new DBManager(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            logger.error("can't connect to db, app finished", e);
            return;
        }
        AuthManager authManager = new AuthManagerImpl();
        authManager.setUsers(dataManager.readUsers());
        CollectionManager collectionManager = new PersonCollectionManager();
        connectionManager = new ServerConnectionManagerImpl();
        ServerCommandManager commandManager = new ServerCommandManagerImpl(collectionManager, dataManager, this, messenger, authManager);
        serverCommandAnalyzer = new ServerCommandAnalyzer(commandManager, messenger);
        try {
            DatagramChannel channel = connectionManager.openConnection(port);
            clientCommandAnalyzer = new ClientCommandAnalyzer(commandManager, channel);
        } catch (IOException e) {
            logger.error("opening connection error", e);
            return;
        }
        collectionManager.setCollection(dataManager.readElements());
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