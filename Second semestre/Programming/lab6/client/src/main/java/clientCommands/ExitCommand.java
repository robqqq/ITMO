package clientCommands;

import client.ClientApplication;
import messages.Messenger;

public class ExitCommand implements ClientCommand {
    private final ClientApplication app;
    private final Messenger messenger;

    public ExitCommand(ClientApplication app, Messenger messenger){
        this.app = app;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        app.exit();
        return messenger.getMsg("exitOutput");
    }
}
