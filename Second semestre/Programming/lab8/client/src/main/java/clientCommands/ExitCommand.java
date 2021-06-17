package clientCommands;

import client.ClientApplication;

public class ExitCommand implements ClientCommand {
    private final ClientApplication app;

    public ExitCommand(ClientApplication app){
        this.app = app;
    }

    @Override
    public void execute() {
        app.exit();
    }
}
