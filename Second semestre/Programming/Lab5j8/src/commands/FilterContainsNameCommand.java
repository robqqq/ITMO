package commands;

import main.ObjectManager;

/**
 * Класс команды, которая выводит элементы, значение поля name которых содержит заданную подстроку
 */
public class FilterContainsNameCommand implements Command{
    private ObjectManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    FilterContainsNameCommand(ObjectManager personManager){
        this.personManager = personManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            personManager.printPersonsContainsName(args[0]);
        } else {
            System.out.println("Invalid arguments: you must specify one argument");
        }
    }
}
