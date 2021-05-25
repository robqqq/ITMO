package serverCommands;

import networkMessages.Request;
import networkMessages.Response;

public class RequestHandler{
    private final ServerCommandManager commandManager;

    public RequestHandler(ServerCommandManager commandManager){
        this.commandManager = commandManager;
    }

    public Response handleRequest(Request request) {
        return commandManager.executeClientCommand(request.getType(), request.getCommand(), request.getArg(),
                request.getObject(), request.getAuth(), request.getLocale());
    }
}
