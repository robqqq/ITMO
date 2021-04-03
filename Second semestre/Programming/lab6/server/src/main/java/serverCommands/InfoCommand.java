package serverCommands;

import collectionManager.CollectionManager;
import messages.Messenger;

/**
 * Класс команды, которая выводит в стандартный поток вывода информацию о коллекции
 */
public class InfoCommand implements ServerCommand{
    private CollectionManager collectionManager;
    private Messenger messenger;

    /**
     * @param collectionManager менеджер коллекции
     * @param messenger мессенджер
     */
    public InfoCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messenger.getMsg("collectionType"))
                .append(": ")
                .append(collectionManager.getType().getSimpleName())
                .append("\n")
                .append(messenger.getMsg("collectionInitDate"))
                .append(": ")
                .append(collectionManager.getInitDate())
                .append("\n")
                .append(messenger.getMsg("collectionSize"))
                .append(": ")
                .append(collectionManager.getSize());
        return stringBuilder.toString();
    }
}
