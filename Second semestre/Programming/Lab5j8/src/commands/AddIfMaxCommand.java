package commands;

import main.EyeColor;
import main.HairColor;
import main.ObjectManager;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.logging.Level;
import static log.Log.logger;

/**
 * Класс команды, которая добавляет новый элемент в коллекцию, если его значение превышает значение
 * наибольшего элемента этой коллекции
 */
public class AddIfMaxCommand implements Command{
    private ObjectManager personManager;
    private CommandManagerInterface commandManager;

    /**
     * Конструктор
     * @param personManager
     * @param commandManager
     */
    AddIfMaxCommand(ObjectManager personManager, CommandManagerInterface commandManager){
        this.personManager = personManager;
        this.commandManager = commandManager;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 9){
            try{
                personManager.addPersonIfMax(args[0], Double.parseDouble(args[1]), Long.parseLong(args[2]),
                        Long.parseLong(args[3]), LocalDate.parse(args[4]).atStartOfDay(),
                        EyeColor.valueOf(args[5].toUpperCase()), HairColor.valueOf(args[6].toUpperCase(Locale.ROOT)),
                        Float.parseFloat(args[7]), Long.parseLong(args[8]), null);
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
        }
        if (args.length == 10){
            try{
                personManager.addPersonIfMax(args[0], Double.parseDouble(args[1]), Long.parseLong(args[2]),
                        Long.parseLong(args[3]), LocalDate.parse(args[4]).atStartOfDay(),
                        EyeColor.valueOf(args[5].toUpperCase()), HairColor.valueOf(args[6].toUpperCase(Locale.ROOT)),
                        Float.parseFloat(args[7]), Long.parseLong(args[8]), args[9]);
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
        } else if (args.length == 3){
            try {
                personManager.addPersonIfMax(args[0], Long.parseLong(args[1]), LocalDate.parse(args[2]).atStartOfDay(), commandManager);
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
