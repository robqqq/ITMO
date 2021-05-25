package client;

import application.Application;
import authManager.ClientAuthManager;
import clientCommands.ClientCommandManager;
import clientCommands.ClientCommandManagerImpl;
import collectionManager.ClientCollectionManager;
import collectionManager.ClientCollectionManagerImpl;
import collectionManager.CollectionSynchronizer;
import connection.ClientConnectionManager;
import connection.ClientConnectionManagerImpl;
import gui.GUIController;

import java.io.IOException;
import java.net.DatagramSocket;


public class ClientApplication implements Application {
    private boolean exit;
    private ClientConnectionManager connectionManager;
    private ClientCommandManager commandManager;
    private ClientAuthManager authManager;
    private String salt;

    public ClientApplication(){
        exit = false;
        salt = "salt";
    }

    @Override
     public void start(){
        try {
            String address = System.getenv("LAB8ADDRESS");
            int port = Integer.parseInt(System.getenv("LAB8PORT"));
            if (address == null) {
                //TODO: обработки отсутсвтия енв вар
            }
            connectionManager = new ClientConnectionManagerImpl();
            DatagramSocket socket = connectionManager.openConnection(address, port);
            socket.setSoTimeout(10000);
            authManager = new ClientAuthManager(salt, connectionManager.getSocketAddress(), socket);
            commandManager = new ClientCommandManagerImpl(socket, connectionManager.getSocketAddress(), this,
                    authManager);
            ClientCollectionManager collectionManager = new ClientCollectionManagerImpl();
            CollectionSynchronizer collectionSynchronizer = new CollectionSynchronizer(commandManager);
            GUIController guiController = new GUIController(collectionManager, authManager, commandManager, collectionSynchronizer);
            guiController.start();
        } catch (IOException e){
            //TODO: обработка проблем с подключением
        } catch (NumberFormatException e){
            //TODO: обработки отсутсвтия енв вар
            System.out.println("Number format exception");
        }
    }

    @Override
    public void exit(){
        exit = true;
        connectionManager.closeConnection();
    }
}