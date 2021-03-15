package commands;

import collectionManager.CollectionManager;
import input.InputManager;

/**
 * Класс команды, которая сохраняет коллекцию в файл
 */
public class SaveCommand implements Command{
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        collectionManager.savePersons();
    }
}
