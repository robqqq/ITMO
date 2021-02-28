package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;

/**
 * Класс команды, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand implements Command{
    private ObjectManager personManager;
    private final String description;

    /**
     * Конструктор
     * @param personManager
     */
    ShowCommand(ObjectManager personManager){
        this.personManager = personManager;
        description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        personManager.printCollection();
    }

    @Override
    public String getHelp() {
        return String.format(": %s", description);
    }
}
