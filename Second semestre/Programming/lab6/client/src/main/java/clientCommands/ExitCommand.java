package clientCommands;

import client.ClientApplication;
import messages.Messenger;

public class ExitCommand implements ClientCommand {
    private ClientApplication app;
    private Messenger messenger;

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
