package commands;

import main.PersonManager;
import java.util.logging.Level;
import static log.Log.logger;

/**
 * Класс команды, которая удаляет элемент из коллекции по его id
 */
public class RemoveByIdCommand implements Command{
    private PersonManager personManager;

    /**
     * Конструктор
     * @param personManager
     */
    RemoveByIdCommand(PersonManager personManager){
        this.personManager = personManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            try{
                int id = Integer.parseInt(args[0]);
                if (!personManager.removePerson(id)){
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
}
