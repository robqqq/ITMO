package serverCommands;

import collectionManager.CollectionManager;
import messages.Messenger;

/**
 * Класс команды, которая очищает коллекцию
 */
public class ClearCommand implements ServerCommand{
    private CollectionManager collectionManager;
    private Messenger messenger;

    /**
     * @param collectionManager менеджер коллекции
     */
    public ClearCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        collectionManager.clear();
        return messenger.getMsg("clearOutput");
    }
}
