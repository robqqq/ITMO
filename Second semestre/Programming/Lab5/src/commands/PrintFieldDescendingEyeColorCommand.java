package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;

/**
 * Класс команды, которая выводит значения поля eyeColor всех элементов в порядке убывания
 */
public class PrintFieldDescendingEyeColorCommand implements Command{
    private ObjectManager personManager;
    private final String description;

    /**
     * Конструктор
     * @param personManager
     */
    PrintFieldDescendingEyeColorCommand(ObjectManager personManager){
        this.personManager = personManager;
        description = "вывести значения поля eyeColor всех элементов в порядке убывания";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        personManager.printSortedPersonsEyeColorField(personManager.getDescendingComparatorByEyeColor());
    }

    @Override
    public String getHelp() {
        return String.format(": %s", description);
    }
}