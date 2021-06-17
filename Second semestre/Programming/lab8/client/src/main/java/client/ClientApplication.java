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
    private ClientConnectionManager connectionManager;
    private ClientCommandManager commandManager;
    private ClientAuthManager authManager;
    private String salt;

    public ClientApplication(){
        salt = "salt";
    }

    @Override
     public void start(){
        try {
            String address = System.getenv("LAB8ADDRESS");
            if (address == null) {
                System.out.println("Incorrect env var LAB8ADDRESS");
            }
            int port;
            try {
                port = Integer.parseInt(System.getenv("LAB8PORT"));
            } catch (NumberFormatException e) {
                System.out.println("Incorrect env var LAB8PORT");
                return;
            }
            connectionManager = new ClientConnectionManagerImpl();
            DatagramSocket socket = connectionManager.openConnection(address, port);
            authManager = new ClientAuthManager(salt, connectionManager.getSocketAddress(), socket);
            commandManager = new ClientCommandManagerImpl(socket, connectionManager.getSocketAddress(), this,
                    authManager);
            ClientCollectionManager collectionManager = new ClientCollectionManagerImpl();
            GUIController guiController = new GUIController(collectionManager, authManager, commandManager, socket,
                    connectionManager.getSocketAddress());
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
        connectionManager.closeConnection();
        System.exit(0);
    }
}