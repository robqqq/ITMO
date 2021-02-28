package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;
import java.util.logging.Level;
import static log.Log.logger;

/**
 * Класс команды, которая обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand implements Command{
    private ObjectManager personManager;
    private final String arguments;
    private final String description;

    UpdateCommand(ObjectManager personManager){
        this.personManager = personManager;
        arguments = "id {element}";
        description = "обновить значение элемента коллекции, id которого равен заданному";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        if (args.length == 1){
            try{
                if (!personManager.updatePerson(Integer.parseInt(args[0]), clientManager)){
                    System.out.println("This id is not exist");
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
