package commands;

import collectionManager.CollectionManager;
import input.InputManager;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию
 */
public class AddCommand implements Command {
    private CollectionManager collectionManager;
    private InputManager inputManager;

    AddCommand(CollectionManager collectionManager, InputManager inputManager){
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        collectionManager.addElement(inputManager.readPerson());
    }
}
