package commands;

import main.Person;
import main.PersonManager;

/**
 * Класс команды, которая выводит любой объект из коллекции, значение поля eyeColor которого является максимальным
 */
public class MaxByEyeColorCommand implements Command{
    PersonManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    public MaxByEyeColorCommand(PersonManager personManager){
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
            personManager.printPerson(max);
        } else {
            System.out.println("Collection is empty");
        }
    }
}
