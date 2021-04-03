package serverCommands;

import application.Application;
import collectionManager.CollectionManager;
import messages.Messenger;

/**
 * Класс команды, которая завершает программу
 */
public class ExitCommand implements ServerCommand{
    private Application app;
    private Messenger messenger;
    private CollectionManager collectionManager;

    /**
     * @param app объект Application
     */
    public ExitCommand(CollectionManager collectionManager, Application app, Messenger messenger){
        this.app = app;
        this.messenger = messenger;
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        collectionManager.savePersons();
        app.exit();
        return messenger.getMsg("exitOutput");
    }
}
