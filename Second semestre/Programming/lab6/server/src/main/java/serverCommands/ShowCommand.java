package serverCommands;

import collectionManager.CollectionManager;
import messages.Messenger;

/**
 * Класс команды, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand implements ServerCommand{
    private CollectionManager collectionManager;
    private Messenger messenger;

    /**
     * @param collectionManager менеджер коллекции
     * @param messenger мессенджер
     */
    public ShowCommand(CollectionManager collectionManager, Messenger messenger){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messenger.getMsg("showOutput"))
                .append(" (")
                .append(collectionManager.getSize())
                .append("):\n");
        collectionManager.getPersonStream()
                .forEachOrdered(person -> stringBuilder.append(messenger.getPersonString(person)).append("\n"));
        return stringBuilder.toString();
    }
}
