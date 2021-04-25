package client;

import application.Application;
import clientCommands.ClientCommandManagerImpl;
import clientCommands.ClientCommandManager;
import connection.ClientConnectionManager;
import connection.ClientConnectionManagerImpl;
import exceptions.ScriptRecursionException;
import input.ConsoleInputManager;
import inputManager.InputManager;
import messages.Messenger;
import messages.MessengerImpl;
import output.ConsoleOutputManager;
import output.OutputManager;

import java.io.IOException;
import java.net.DatagramSocket;


public class ClientApplication implements Application {
    private boolean exit;
    private Messenger messenger;
    private OutputManager outputManager;
    private InputManager inputManager;
    private ClientConnectionManager connectionManager;
    private ClientCommandManager commandManager;
    private String address;
    private int port;
    private DatagramSocket socket;

    public ClientApplication(String address, int port){
        exit = false;
        this.address = address;
        this.port = port;
    }

    @Override
     public void start(){
        try {
            outputManager = new ConsoleOutputManager();
            messenger = new MessengerImpl();
            connectionManager = new ClientConnectionManagerImpl();
            socket = connectionManager.openConnection(address, port);
            inputManager = new ConsoleInputManager(messenger, outputManager);
            commandManager = new ClientCommandManagerImpl(socket, connectionManager.getSocketAddress(),
                    inputManager, outputManager, messenger, this);
            outputManager.printMsg(messenger.getMsg("start") + "\n");
            run();
        } catch (IOException e){
            outputManager.printErrorMsg(messenger.getMsg("noConnection") + "\n");
        }
    }

    private void run() throws IOException {
        while (!exit) {
            String inputString;
            try {
                inputString = inputManager.readCommand();
                if (inputString.equals("")) continue;
                String[] input = inputString.split("\\s+", 2);
                if (input.length == 1) {
                    commandManager.executeCommand(input[0], null);
                } else {
                    commandManager.executeCommand(input[0], input[1]);
                }
            } catch (IOException e) {
                outputManager.printErrorMsg(messenger.getMsg("endOfInput") + "\n");
                exit();
            } catch (ScriptRecursionException e){
                outputManager.printErrorMsg(messenger.getMsg("scriptRecursion") + "\n");
            }
        }
    }

    @Override
    public void exit(){
        exit = true;
        connectionManager.closeConnection();
    }
}