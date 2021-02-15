package commands;

import main.PersonManager;

/**
 * Класс команды, которая выводит в стандартный поток вывода информацию о коллекции
 */
public class InfoCommand implements Command{
    private PersonManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    InfoCommand(PersonManager personManager){
        this.personManager = personManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Type: " + personManager.getType().getSimpleName());
        System.out.println("Initialization date: " + personManager.getInitDate());
        System.out.println("Number of elements: " + personManager.getLength());
    }
}
