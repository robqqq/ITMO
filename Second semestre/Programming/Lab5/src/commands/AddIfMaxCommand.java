package commands;

import collectionManager.CollectionManager;
import input.InputManager;
import person.Person;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию, если его значение превышает значение
 * наибольшего элемента этой коллекции
 */
public class AddIfMaxCommand implements Command{
    private CollectionManager collectionManager;
    private InputManager inputManager;

    /**
     * @param collectionManager менеджер коллекции
     * @param inputManager менеджер ввода
     */
    public AddIfMaxCommand(CollectionManager collectionManager, InputManager inputManager){
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute() {
        Person person = inputManager.readPerson();
        if (person.compareTo(collectionManager.getPersonStream().max(Person::compareTo).get()) >= 0)
            collectionManager.addElement(person);
    }
}
