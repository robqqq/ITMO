package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;

/**
 * Класс команды, которая выводит в стандартный поток вывода информацию о коллекции
 */
public class InfoCommand implements Command{
    private ObjectManager personManager;
    private final String description;

    /**
     * Конструктор
     * @param personManager
     */
    InfoCommand(ObjectManager personManager){
        this.personManager = personManager;
        description = "вывести в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        System.out.println("Type: " + personManager.getType().getSimpleName());
        System.out.println("Initialization date: " + personManager.getInitDate());
        System.out.println("Number of elements: " + personManager.getLength());
    }

    @Override
    public String getHelp() {
        return String.format(": %s", description);
    }
}
