package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;
import main.Person;

/**
 * Класс команды, которая выводит любой объект из коллекции, значение поля eyeColor которого является максимальным
 */
public class MaxByEyeColorCommand implements Command{
    ObjectManager personManager;
    private final String description;

    /**
     * Конструктор
     * @param personManager
     */
    public MaxByEyeColorCommand(ObjectManager personManager){
        this.personManager = personManager;
        description = "вывести любой объект из коллекции, значение поля eyeColor которого является максимальным";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        Person max = personManager.getMax(personManager.getComparatorByEyeColor());
        if (max != null){
            personManager.printElement(max);
        } else {
            System.out.println("Collection is empty");
        }
    }

    @Override
    public String getHelp() {
        return String.format(": %s", description);
    }
}
