package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего
 * элемента этой коллекции
 */
public class AddIfMinCommand implements Command{
    private ObjectManager personManager;
    private final String arguments;
    private final String description;

    AddIfMinCommand(ObjectManager personManager){
        this.personManager = personManager;
        arguments = "{element}";
        description = "добавить новый элемент в коллекцию, если его значение меньше, " +
                "чем у наименьшего элемента этой коллекции";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        personManager.addPersonIfMin(clientManager);
    }

    @Override
    public String getHelp() {
        return String.format("%s : %s", arguments, description);
    }
}
