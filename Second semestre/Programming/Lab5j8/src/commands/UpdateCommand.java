package commands;

import main.EyeColor;
import main.HairColor;
import main.PersonManager;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import static log.Log.logger;

/**
 * Класс команды, которая обновляет значение элемента коллекции, id которого равен заданному
 */
public class UpdateCommand implements Command{
    private PersonManager personManager;
    private CommandManager commandManager;

    /**
     * Конструктор
     * @param personManager
     * @param commandManager
     */
    UpdateCommand(PersonManager personManager, CommandManager commandManager){
        this.personManager = personManager;
        this.commandManager = commandManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 10){
            try{
                if (!personManager.updatePerson(Integer.parseInt(args[0]), args[1], Double.parseDouble(args[2]), Long.parseLong(args[3]),
                        Long.parseLong(args[4]), LocalDate.parse(args[5]).atStartOfDay(),
                        EyeColor.valueOf(args[6].toUpperCase()), HairColor.valueOf(args[7].toUpperCase()),
                        Float.parseFloat(args[8]), Long.parseLong(args[9]), null)){
                    System.out.println("An element with this id does not exist");
                };
            } catch (NumberFormatException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid arguments");
            } catch (DateTimeParseException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid arguments: the date must be in format yyyy-MM-dd");
            } catch (IllegalArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid arguments: this color does not exist");
            }
        } else if (args.length == 11){
            try{
                if(!personManager.updatePerson(Integer.parseInt(args[0]), args[1], Double.parseDouble(args[2]), Long.parseLong(args[3]),
                        Long.parseLong(args[4]), LocalDate.parse(args[5]).atStartOfDay(),
                        EyeColor.valueOf(args[6].toUpperCase()), HairColor.valueOf(args[7].toUpperCase()),
                        Float.parseFloat(args[8]), Long.parseLong(args[9]), args[10])){
                    System.out.println("An element with this id does not exist");
                }
            } catch (NumberFormatException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid arguments");
            } catch (DateTimeParseException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid arguments: the date must be in format yyyy-MM-dd");
            } catch (IllegalArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid arguments: this color does not exist");
            }
        } else if (args.length == 4){
            try {
                if (!personManager.updatePerson(Integer.parseInt(args[0]), args[1], Long.parseLong(args[2]),
                        LocalDate.parse(args[3]).atStartOfDay(), commandManager)){
                    System.out.println("An element with this id does not exist");
                }
            } catch (DateTimeParseException e) {
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid argument: the date must be in format yyyy-MM-dd");
            } catch (NumberFormatException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid argument: value of height must be long");
            }
        } else {
            System.out.println("Invalid arguments: you must specify free or ten arguments");
        }
    }
}
