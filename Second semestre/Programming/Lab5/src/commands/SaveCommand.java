package commands;

import collectionManager.CollectionManager;

/**
 * Класс команды, которая сохраняет коллекцию в файл
 */
public class SaveCommand implements Command{
    private CollectionManager collectionManager;

    /**
     * @param collectionManager менеджер коллекции
     */
    public SaveCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.savePersons();
    }
}
