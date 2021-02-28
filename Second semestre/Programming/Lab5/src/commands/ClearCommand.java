package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;

/**
 * Класс команды, которая очищает коллекцию
 */
public class ClearCommand implements Command{
    private ObjectManager personManager;
    private final String description;

    /**
     * Конструктор
     * @param personManager
     */
    ClearCommand(ObjectManager personManager){
        this.personManager = personManager;
        description = "очистить коллекцию";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        personManager.removeAll();
    }

    @Override
    public String getHelp() {
        return String.format(": %s", description);
    }
}
