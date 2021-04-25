package serverCommands;

import collectionManager.CollectionManager;
import dbManager.DataManager;
import messages.Messenger;

/**
 * Класс команды, которая очищает коллекцию
 */
public class ClearCommand implements ServerCommand{
    private final CollectionManager collectionManager;
    private final DataManager dataManager;
    private final Messenger messenger;

    public ClearCommand(CollectionManager collectionManager, DataManager dataManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.dataManager = dataManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        dataManager.clearElements();
        collectionManager.clear();
        return messenger.getMsg("clearOutput");
    }
}
