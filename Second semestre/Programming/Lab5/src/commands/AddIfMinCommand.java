package commands;

import collectionManager.CollectionManager;
import input.InputManager;
import person.Person;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего
 * элемента этой коллекции
 */
public class AddIfMinCommand implements Command{
    private CollectionManager collectionManager;
    private InputManager inputManager;

    /**
     * @param collectionManager менеджер коллекции
     * @param inputManager менеджер ввода
     */
    public AddIfMinCommand(CollectionManager collectionManager, InputManager inputManager){
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute() {
        Person person = inputManager.readPerson();
        if (person.compareTo(collectionManager.getPersonStream().min(Person::compareTo).get()) <= 0)
            collectionManager.addElement(person);
    }
}
