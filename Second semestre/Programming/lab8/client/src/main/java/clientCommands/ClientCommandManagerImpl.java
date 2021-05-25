package clientCommands;

import authManager.ClientAuthManager;
import client.ClientApplication;
import exceptions.NoArgException;
import networkMessages.Response;
import networkMessages.ResponseImpl;
import networkMessages.ResponseType;
import person.RawPerson;
import requests.ClientRequestFactory;
import requests.ClientRequestSender;
import requests.RequestFactory;
import requests.RequestSender;
import responses.ClientResponseReceiver;
import responses.ResponseReceiver;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.*;

public class ClientCommandManagerImpl implements ClientCommandManager {
    private final DatagramSocket socket;
    private final SocketAddress address;
    private final Map<String, ClientCommand> clientCommandMap;
    private final ClientCommandInvoker commandInvoker;
    private final ClientAuthManager authManager;
    private static final Set<String> usedScripts = new HashSet<>();

    public ClientCommandManagerImpl(DatagramSocket socket, SocketAddress address, ClientApplication app,
                                    ClientAuthManager authManager){
        this.socket = socket;
        this.address = address;
        this.authManager = authManager;
        commandInvoker = new ClientCommandInvokerImpl();
        clientCommandMap = new HashMap<>();
        clientCommandMap.put("exit", new ExitCommand(app));
        clientCommandMap.put("execute_script", new ExecuteScriptCommand(socket, address, app));
    }

    @Override
    public void usedScriptAdd(String scriptName) {
        usedScripts.add(scriptName);
    }

    @Override
    public boolean scriptIsUsed(String scriptName) {
        return usedScripts.contains(scriptName);
    }

    @Override
    public synchronized Response executeCommand(String command, String arg, RawPerson person) throws IOException {
        if (clientCommandMap.containsKey(command)){
            return executeClientCommand(command, arg);
        } else {
            return executeServerCommand(command, arg, person);
        }
    }

    @Override
    public void clearUsedScripts() {
        usedScripts.clear();
    }

    @Override
    public void usedScriptRemove(String scriptName) {
        usedScripts.remove(scriptName);
    }

    private Response executeServerCommand(String command, String arg, RawPerson person) throws IOException {
        RequestSender requestSender = new ClientRequestSender(address, socket);
        RequestFactory requestFactory = new ClientRequestFactory();
        if (person == null) {
            if (arg == null || arg.equals("")) {
                requestSender.sendRequest(requestFactory.createSimpleRequest(command, authManager.getAuth()));
            } else {
                requestSender.sendRequest(requestFactory.createArgRequest(command, arg, authManager.getAuth()));
            }
        } else {
            if (arg == null || arg.equals("")) {
                requestSender.sendRequest(requestFactory.createObjectRequest(command, person, authManager.getAuth()));
            } else {
                requestSender.sendRequest(requestFactory.createArgObjectRequest(command, arg, person, authManager.getAuth()));
            }
        }
        ResponseReceiver responseReceiver = new ClientResponseReceiver(socket);
        return responseReceiver.receiveResponse();
    }

    private Response executeClientCommand(String command, String arg){
        commandInvoker.setArg(arg);
        try {
            clientCommandMap.get(command).acceptInvoker(commandInvoker);
            return new ResponseImpl(ResponseType.CLIENT_RESPONSE, commandInvoker.getCommandOutput(), null);
        } catch (NoArgException e) {
            return new ResponseImpl(ResponseType.CLIENT_RESPONSE, ResourceBundle.getBundle("messages").getString("err.no_arg"), null);
        }
    }
}
