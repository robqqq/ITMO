package commands;

import main.ObjectManager;

/**
 * Класс команды, которая выводит значения поля eyeColor всех элементов в порядке убывания
 */
public class PrintFieldDescendingEyeColorCommand implements Command{
    private ObjectManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    PrintFieldDescendingEyeColorCommand(ObjectManager personManager){
        this.personManager = personManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        personManager.printSortedPersonsEyeColorField(personManager.getDescendingComparatorByEyeColor());
    }
}