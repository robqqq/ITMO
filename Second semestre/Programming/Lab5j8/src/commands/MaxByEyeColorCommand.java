package commands;

import main.ObjectManager;
import main.Person;

/**
 * Класс команды, которая выводит любой объект из коллекции, значение поля eyeColor которого является максимальным
 */
public class MaxByEyeColorCommand implements Command{
    ObjectManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    public MaxByEyeColorCommand(ObjectManager personManager){
        this.personManager = personManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        Person max = personManager.getMax(personManager.getComparatorByEyeColor());
        if (max != null){
            personManager.printElement(max);
        } else {
            System.out.println("Collection is empty");
        }
    }
}
