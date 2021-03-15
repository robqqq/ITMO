package commands;

import collectionManager.CollectionManager;
import messages.Messenger;
import output.OutputManager;

/**
 * Класс команды, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand implements Command{
    private CollectionManager collectionManager;
    private Messenger messenger;
    private OutputManager outputManager;

    ShowCommand(CollectionManager collectionManager, Messenger messenger, OutputManager outputManager){
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.outputManager = outputManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        collectionManager.getPersonStream()
                .forEachOrdered(person -> outputManager.printMsg(messenger.getPersonString(person) + "\n"));
    }
}