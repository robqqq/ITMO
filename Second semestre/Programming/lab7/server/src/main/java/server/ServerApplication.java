package server;

import application.Application;
import authManager.AuthManager;
import authManager.AuthManagerImpl;
import collectionManager.CollectionManager;
import collectionManager.PersonCollectionManager;
import connection.ServerConnectionManager;
import connection.ServerConnectionManagerImpl;
import dataManager.*;
import messages.Messenger;
import messages.MessengerImpl;
import requests.RequestReceiver;
import requests.ServerRequestReceiver;
import responses.ResponseSender;
import responses.ServerResponseSender;
import serverCommands.*;
import serverCommands.RequestHandler;
import serverCommands.CommandHandler;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServerApplication implements Application {
    private final int port;
    private ServerCommandHandler serverCommandHandler;
    private Server server;
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
        DataManager dataManager;
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
        serverCommandHandler = new ServerCommandHandler(commandManager, messenger);
        RequestReceiver requestReceiver;
        ResponseSender responseSender;
        try {
            DatagramChannel channel = connectionManager.openConnection(port);
            requestReceiver = new ServerRequestReceiver(channel);
            responseSender = new ServerResponseSender(channel);
        } catch (IOException e) {
            logger.error("opening connection error", e);
            return;
        }
        RequestHandler requestHandler = new RequestHandler(commandManager);
        server = new Server(requestReceiver, requestHandler, responseSender);
        collectionManager.setCollection(dataManager.readElements());
        logger.info("server has started");
        serverCommandHandler.startReading();
        server.start();
    }

    @Override
    public void exit(){
        try {
            connectionManager.closeConnection();
        } catch (IOException e) {
            logger.error("connection closing error", e);
        }
        server.shutdownExecutorServices();
        serverCommandHandler.stopReading();
    }
}