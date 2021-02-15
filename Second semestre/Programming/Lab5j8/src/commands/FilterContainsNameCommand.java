package commands;

import main.PersonManager;

/**
 * Класс команды, которая выводит элементы, значение поля name которых содержит заданную подстроку
 */
public class FilterContainsNameCommand implements Command{
    private PersonManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    FilterContainsNameCommand(PersonManager personManager){
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
