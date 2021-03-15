package commands;

import collectionManager.CollectionManager;

/**
 * Класс команды, которая очищает коллекцию
 */
public class ClearCommand implements Command{
    private CollectionManager collectionManager;

    ClearCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        collectionManager.clear();
    }
}
