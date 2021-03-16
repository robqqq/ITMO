package commands;

import collectionManager.CollectionManager;

/**
 * Класс команды, которая очищает коллекцию
 */
public class ClearCommand implements Command{
    private CollectionManager collectionManager;

    /**
     * @param collectionManager менеджер коллекции
     */
    public ClearCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.clear();
    }
}
