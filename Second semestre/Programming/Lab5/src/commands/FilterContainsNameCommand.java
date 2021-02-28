package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;

/**
 * Класс команды, которая выводит элементы, значение поля name которых содержит заданную подстроку
 */
public class FilterContainsNameCommand implements Command{
    private ObjectManager personManager;
    private final String arguments;
    private final String description;

    /**
     * Конструктор
     * @param personManager
     */
    FilterContainsNameCommand(ObjectManager personManager){
        this.personManager = personManager;
        arguments = "name";
        description = "вывести элементы, значение поля name которых содержит заданную подстроку";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        if (args.length == 1) {
            personManager.printPersonsContainsName(args[0]);
        } else {
            System.out.println("Invalid arguments: you must specify one argument");
        }
    }

    @Override
    public String getHelp() {
        return String.format("%s : %s", arguments, description);
    }
}
