package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;

/**
 * Класс команды, которая сохраняет коллекцию в файл
 */
public class SaveCommand implements Command{
    private ObjectManager personManager;
    private final String description;

    /**
     * Конструктор
     * @param personManager
     */
    SaveCommand(ObjectManager personManager){
        this.personManager = personManager;
        description = "сохранить коллекцию в файл";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        personManager.writeToFile();
    }

    @Override
    public String getHelp() {
        return String.format(": %s", description);
    }
}
