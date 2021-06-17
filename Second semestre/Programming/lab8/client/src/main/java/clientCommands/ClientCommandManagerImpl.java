package clientCommands;

import authManager.ClientAuthManager;
import client.ClientApplication;
import exceptions.NoArgException;
import exceptions.ScriptException;
import exceptions.ScriptRecursionException;
import input.InputManager;
import input.ScriptInputManager;
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

import java.io.*;
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
        clientCommandMap.put("execute_script", new ExecuteScriptCommand(this));
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
    public void executeCommand(String command, String arg, RawPerson person) throws IOException {
        if (clientCommandMap.containsKey(command)){
            executeClientCommand(command, arg);
        } else {
            executeServerCommand(command, arg, person);
        }
    }

    @Override
    public void executeScript(File file) throws IOException{
        InputManager inputManager = new ScriptInputManager(
                new BufferedReader(new InputStreamReader(new FileInputStream(file))));
        if (scriptIsUsed(file.getAbsolutePath())){
            clearUsedScripts();
            throw new ScriptRecursionException();
        }
        usedScriptAdd(file.getAbsolutePath());
        while (inputManager.ready()){
            String inputString = inputManager.readCommand();
            String[] input = inputString.split("\\s+", 2);
            RawPerson person = null;
            if (input[0].equals("add") || input[0].equals("update") || input[0].equals("add_if_max")
                    || input[0].equals("add_if_min")){
                person = inputManager.readPerson();
            }
            if (input.length < 2)
                executeCommand(input[0], null, person);
            else
                executeCommand(input[0], input[1], person);
        }
        usedScriptRemove(file.getAbsolutePath());
    }

    @Override
    public void clearUsedScripts() {
        usedScripts.clear();
    }

    @Override
    public void usedScriptRemove(String scriptName) {
        usedScripts.remove(scriptName);
    }

    private void executeServerCommand(String command, String arg, RawPerson person) throws IOException {
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
    }

    private void executeClientCommand(String command, String arg){
        commandInvoker.setArg(arg);
        try {
            clientCommandMap.get(command).acceptInvoker(commandInvoker);
        } catch (NoArgException ignored) {

        }
    }
}
