package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию
 */
public class AddCommand implements Command {
    private ObjectManager personManager;
    private final String arguments;
    private final String description;

    AddCommand(ObjectManager personManager){
        this.personManager = personManager;
        arguments = "{element}";
        description = "добавить новый элемент в коллекцию";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        personManager.addPerson(clientManager);
    }

    @Override
    public String getHelp() {
        return String.format("%s : %s", arguments, description);
    }
}
