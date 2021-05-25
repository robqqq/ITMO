package serverCommands;

import application.Application;
import auth.Auth;
import authManager.AuthManager;
import collectionManager.CollectionManager;
import dataManager.DataManager;
import exceptions.AuthException;
import exceptions.InvalidArgumentTypeException;
import exceptions.NeedObjectException;
import exceptions.NoArgException;
import networkMessages.RequestType;
import networkMessages.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.RawPerson;
import responses.ResponseFactory;
import responses.ServerResponseFactory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

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
    private final CollectionManager collectionManager;
    private static final Logger logger = LoggerFactory.getLogger(ServerCommandManagerImpl.class);

    public ServerCommandManagerImpl(CollectionManager collectionManager, DataManager dataManager, Application app, AuthManager authManager){
        this.authManager = authManager;
        this.collectionManager = collectionManager;
        clientCommandMap = new HashMap<>();
        serverCommandMap = new HashMap<>();
        history = new Stack<>();
        commandInvoker = new ServerCommandInvokerImpl();
        commandInvokerForServerCommands = new ServerCommandInvokerImpl();
        clientCommandMap.put("help", new HelpCommand());
        clientCommandMap.put("info", new InfoCommand(collectionManager));
        clientCommandMap.put("show", new ShowCommand());
        clientCommandMap.put("add", new AddCommand(collectionManager, dataManager));
        clientCommandMap.put("update", new UpdateCommand(collectionManager, dataManager));
        clientCommandMap.put("remove_by_id", new RemoveByIdCommand(collectionManager, dataManager));
        clientCommandMap.put("clear", new ClearCommand(collectionManager, dataManager));
        clientCommandMap.put("add_if_max", new AddIfMaxCommand(collectionManager, dataManager));
        clientCommandMap.put("add_if_min", new AddIfMinCommand(collectionManager, dataManager));
        clientCommandMap.put("history", new HistoryCommand(history));
        clientCommandMap.put("max_by_eye_color", new MaxByEyeColorCommand(collectionManager));
        clientCommandMap.put("filter_contains_name", new FilterContainsNameCommand(collectionManager));
        clientCommandMap.put("print_field_descending_eye_color", new PrintFieldDescendingEyeColorCommand(collectionManager));
        clientCommandMap.put("auth", new AuthCommand(authManager));
        clientCommandMap.put("reg", new RegCommand(authManager, dataManager));
        clientCommandMap.put("disconnect", new DisconnectCommand(authManager));

        serverCommandMap.put("exit", new ExitCommand(app));
    }

    @Override
    public Response executeClientCommand(RequestType type, String command, String arg, RawPerson person, Auth auth, Locale locale){
        ResponseFactory responseFactory = new ServerResponseFactory();
        if (!authManager.checkAuth(auth) && type != RequestType.AUTH_REG_COMMAND){
            return responseFactory.createAuthErrorResponse("authError", null);
        }
        if (clientCommandMap.containsKey(command)) {
            commandInvoker.setType(type);
            commandInvoker.setArg(arg);
            commandInvoker.setObject(person);
            commandInvoker.setAuth(auth);
            commandInvoker.setLocale(locale);
            try {
                clientCommandMap.get(command).acceptInvoker(commandInvoker);
                if (!command.equals("show") && !command.equals("auth") && !command.equals("reg") &&
                        !command.equals("disconnect")) history.push(command);
                if (history.size() > 9) history.remove(0);
                return responseFactory.createDefaultResponse(commandInvoker.getCommandOutput(), collectionManager.getPersonStream());
            } catch (NoArgException e) {
                return responseFactory.createErrorResponse("noArg", collectionManager.getPersonStream());
            } catch (InvalidArgumentTypeException e) {
                return responseFactory.createErrorResponse("invalidArgumentType", collectionManager.getPersonStream());
            } catch (NeedObjectException e) {
                return responseFactory.createNeedObjectResponse(collectionManager.getPersonStream());
            } catch (AuthException e){
                return responseFactory.createAuthErrorResponse("authError", collectionManager.getPersonStream());
            }

        } else {
            return responseFactory.createErrorResponse("noSuchCommand", collectionManager.getPersonStream());
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