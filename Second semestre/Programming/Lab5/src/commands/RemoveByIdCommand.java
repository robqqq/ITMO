package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;
import java.util.logging.Level;
import static log.Log.logger;

/**
 * Класс команды, которая удаляет элемент из коллекции по его id
 */
public class RemoveByIdCommand implements Command{
    private ObjectManager personManager;
    private final String arguments;
    private final String description;

    /**
     * Конструктор
     * @param personManager
     */
    RemoveByIdCommand(ObjectManager personManager){
        this.personManager = personManager;
        arguments = "id";
        description = "удалить элемент из коллекции по его id";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        if (args.length == 1){
            try{
                int id = Integer.parseInt(args[0]);
                if (!personManager.removeElement(id)){
                    System.out.println("An element with this id does not exist");
                }
            } catch (NumberFormatException e){
                logger.log(Level.WARNING, e.getMessage(), e);
                System.out.println("Invalid arguments: the argument must be an integer");
            }
        } else {
            System.out.println("Invalid arguments: you must specify one argument");
        }
    }

    @Override
    public String getHelp() {
        return String.format("%s : %s", arguments, description);
    }
}
