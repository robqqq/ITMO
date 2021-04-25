package serverCommands;

import application.Application;
import auth.Auth;
import authManager.AuthManager;
import collectionManager.CollectionManager;
import dbManager.DataManager;
import exceptions.AuthException;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import messages.Messenger;
import networkMessages.RequestType;
import person.RawPerson;
import responses.ResponseFactory;
import responses.ResponseSender;
import responses.ServerResponseFactory;
import responses.ServerResponseSender;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Реализация интерфеса CommandManager
 */
public class ServerCommandManagerImpl implements ServerCommandManager {
    private final Map<String, ServerCommand> clientCommandMap;
    private final Map<String, ServerCommand> serverCommandMap;
    private final Stack<String> history;
    private final ServerCommandInvoker commandInvoker;
    private final ServerCommandInvoker commandInvokerForServerCommands;
    private final AuthManager authManager;
    private static final Logger logger = LoggerFactory.getLogger(ServerCommandManagerImpl.class);

    public ServerCommandManagerImpl(CollectionManager collectionManager, DataManager dataManager, Application app, Messenger messenger, AuthManager authManager){
        this.authManager = authManager;
        clientCommandMap = new HashMap<>();
        serverCommandMap = new HashMap<>();
        history = new Stack<>();
        commandInvoker = new ServerCommandInvokerImpl();
        commandInvokerForServerCommands = new ServerCommandInvokerImpl();
        clientCommandMap.put("help", new HelpCommand(clientCommandMap.keySet(), messenger));
        clientCommandMap.put("info", new InfoCommand(collectionManager, messenger));
        clientCommandMap.put("show", new ShowCommand(collectionManager, messenger));
        clientCommandMap.put("add", new AddCommand(collectionManager, dataManager, messenger));
        clientCommandMap.put("update", new UpdateCommand(collectionManager, dataManager, messenger));
        clientCommandMap.put("remove_by_id", new RemoveByIdCommand(collectionManager, dataManager, messenger));
        clientCommandMap.put("clear", new ClearCommand(collectionManager, dataManager, messenger));
        clientCommandMap.put("add_if_max", new AddIfMaxCommand(collectionManager, dataManager, messenger));
        clientCommandMap.put("add_if_min", new AddIfMinCommand(collectionManager, dataManager, messenger));
        clientCommandMap.put("history", new HistoryCommand(history, messenger));
        clientCommandMap.put("max_by_eye_color", new MaxByEyeColorCommand(collectionManager, messenger));
        clientCommandMap.put("filter_contains_name", new FilterContainsNameCommand(collectionManager, messenger));
        clientCommandMap.put("print_field_descending_eye_color", new PrintFieldDescendingEyeColorCommand(collectionManager, messenger));
        clientCommandMap.put("auth", new AuthCommand(messenger, authManager));
        clientCommandMap.put("reg", new RegCommand(messenger, authManager, dataManager));

        serverCommandMap.put("exit", new ExitCommand(collectionManager, app, messenger));
    }

    @Override
    public void executeClientCommand(RequestType type, String command, String arg, RawPerson person, Auth auth,
                                     SocketAddress address, DatagramChannel channel){
        ResponseFactory responseFactory = new ServerResponseFactory();
        ResponseSender responseSender = new ServerResponseSender(channel, address);
        if (type == RequestType.AUTH_REG_COMMAND){
            commandInvoker.setAuthArg(auth);
        } else if (!authManager.checkAuth(auth)){
            responseSender.sendResponse(responseFactory.createAuthErrorResponse("authError"));//TODO: добавить authError в мессенджер
            return;
        }
        if (clientCommandMap.containsKey(command)) {
            commandInvoker.setType(type);
            commandInvoker.setArg(arg);
            commandInvoker.setObject(person);
            try {
                clientCommandMap.get(command).acceptInvoker(commandInvoker);
                history.push(command);
                if (history.size() > 9) {
                    history.remove(0);
                }
                responseSender.sendResponse(responseFactory.createDefaultResponse(commandInvoker.getCommandOutput()));
            } catch (NoArgException e) {
                responseSender.sendResponse(responseFactory.createErrorResponse("noArg"));
            } catch (InvalidArgumentTypeException e) {
                responseSender.sendResponse((responseFactory.createErrorResponse("invalidArgumentType")));
            } catch (NeedObjectException e) {
                responseSender.sendResponse(responseFactory.createNeedObjectResponse());
            } catch (AuthException e){
                responseSender.sendResponse(responseFactory.createAuthErrorResponse("authError"));//TODO: добавить authError в мессенджер
            }

        } else {
            responseSender.sendResponse(responseFactory.createErrorResponse("noSuchCommand"));
        }
    }

    @Override
    public void executeServerCommand(String command) {
        if (serverCommandMap.containsKey(command)){
            try {
                serverCommandMap.get(command).acceptInvoker(commandInvokerForServerCommands);
                logger.info(commandInvokerForServerCommands.getCommandOutput());
            } catch (NoArgException | InvalidArgumentTypeException | NeedObjectException ignored) {

            }
        } else {
            logger.error("no such command: " + command);
        }
    }
}