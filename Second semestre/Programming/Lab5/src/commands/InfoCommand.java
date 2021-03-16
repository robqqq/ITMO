package commands;

import collectionManager.CollectionManager;
import messages.Messenger;
import output.OutputManager;

/**
 * Класс команды, которая выводит в стандартный поток вывода информацию о коллекции
 */
public class InfoCommand implements Command{
    private CollectionManager collectionManager;
    private Messenger messenger;
    private OutputManager outputManager;

    /**
     * @param collectionManager менеджер коллекции
     * @param messenger мессенджер
     * @param outputManager менеджер вывода
     */
    public InfoCommand(CollectionManager collectionManager, Messenger messenger, OutputManager outputManager){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.outputManager = outputManager;
    }

    @Override
    public void execute() {
        outputManager.printMsg(messenger.getCollectionTypeMsg() + ": " + collectionManager.getType().getSimpleName() +
                "\n" + messenger.getCollectionInitDateMsg() + ": " + collectionManager.getInitDate() +
                "\n" + messenger.getCollectionSizeMsg() + ": " + collectionManager.getSize() + "\n");
    }
}
