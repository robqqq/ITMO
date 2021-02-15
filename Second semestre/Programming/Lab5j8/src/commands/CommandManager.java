package commands;

import exceptions.InvalidArgumentException;
import main.EyeColor;
import main.HairColor;
import main.PersonManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import static log.Log.logger;

/**
 * Класс, который производит чтение с консоли и отправляет запросы командам
 */
public class CommandManager {
    private PersonManager personManager;
    private Map<String, Command> commandMap;
    private String[] in;
    private String command;
    private String[] args;
    private Stack<String> history = new Stack<>();
    private BufferedReader reader;
    private Set<String> usedScripts;

    /**
     * Конструктор
     * @param personManager
     */
    public CommandManager(PersonManager personManager){
        this.personManager = personManager;
        usedScripts = new HashSet<>();
        reader = new BufferedReader(new InputStreamReader(System.in));
        commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand());
        commandMap.put("info", new InfoCommand(personManager));
        commandMap.put("show", new ShowCommand(personManager));
        commandMap.put("add", new AddCommand(personManager, this));
        commandMap.put("update", new UpdateCommand(personManager, this));
        commandMap.put("remove_by_id", new RemoveByIdCommand(personManager));
        commandMap.put("clear", new ClearCommand(personManager));
        commandMap.put("save", new SaveCommand(personManager));
        commandMap.put("execute_script", new ExecuteScriptCommand(this));
        commandMap.put("exit", new ExitCommand());
        commandMap.put("add_if_max", new AddIfMaxCommand(personManager, this));
        commandMap.put("add_if_min", new AddIfMinCommand(personManager, this));
        commandMap.put("history", new HistoryCommand(history));
        commandMap.put("max_by_eye_color", new MaxByEyeColorCommand(personManager));
        commandMap.put("filter_contains_name", new FilterContainsNameCommand(personManager));
        commandMap.put("print_field_descending_eye_color", new PrintFieldDescendingEyeColorCommand(personManager));
    }

    /**
     * Метод, который запускает программу
     */
    public void start(){
        logger.log(Level.INFO, "start program");
        try {
            personManager.readFromFile();
        } catch (InvalidArgumentException e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            System.err.println(e.getMessage());
            System.exit(1);
        }
        readCommands(reader, "> ");
    }

    /**
     * Метод, который запускает чтение команд
     * @param reader
     * @param startSymbol
     */
    void readCommands(BufferedReader reader, String startSymbol){
        try{
            while (true) {
                System.out.print(startSymbol);
                in = reader.readLine().trim().split("\\s+");
                if (in[0].length() == 0) {

                } else {
                    command = in[0].toLowerCase();
                    args = new String[in.length - 1];
                    for (int i = 0; i < args.length; i++) {
                        args[i] = in[i + 1];
                    }
                    if (commandMap.containsKey(command)) {
                        commandMap.get(command).execute(args);
                        history.push(command);
                        if (history.size() > 9) {
                            history.remove(0);
                        }
                    } else {
                        System.out.println("Unknown command: " + command);
                    }
                }
            }
        } catch (IOException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
        } catch (NullPointerException e){
            usedScripts.clear();
        }
    }

    /**
     * Метод, который добавляет скрипт в историю использованных скриптов
     * @param scriptName
     */
    void usedScriptAdd(String scriptName){
        usedScripts.add(scriptName);
    }

    /**
     * Метод, который проверяет наличие скрипта в истории использованных скриптов
     * @param scriptName
     * @return
     */
    boolean scriptIsUsed(String scriptName){
        return usedScripts.contains(scriptName);
    }

    /**
     * Метод, который запрашивает ввод параметра coordinates.x
     * @return
     */
    public Double askCoordinatesX(){
        System.out.print("Value of coordinates.x: ");
        Double coordinatesX;
        while (true) {
            try {
                coordinatesX = Double.parseDouble(reader.readLine().trim());
                break;
            } catch (NumberFormatException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Value of coordinates.x must be double");
                System.out.print("Value of coordinates.x: ");
            } catch (IOException e) {
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println(e.getMessage());
            }
        }
        return coordinatesX;
    }

    /**
     * Метод, который запрашивает ввод параметра coordinates.Y
     * @return
     */
    public long askCoordinatesY(){
        System.out.print("Value of coordinates.y: ");
        long coordinatesY;
        while (true) {
            try {
                coordinatesY = Long.parseLong(reader.readLine().trim());
                break;
            } catch (NumberFormatException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Value of coordinates.y must be long");
                System.out.print("Value of coordinates.y: ");
            } catch (IOException e) {
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println(e.getMessage());
            }
        }
        return coordinatesY;
    }

    /**
     * Метод, который запрашивает ввод параметра eyeColor
     * @return
     */
    public String askEyeColor(){
        System.out.printf("Value of eyeColor(%s, %s, %s): ", EyeColor.BLACK, EyeColor.ORANGE, EyeColor.WHITE);
        String eyeColor = null;
        try {
            eyeColor = reader.readLine().toUpperCase().trim();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
        }
        return eyeColor;
    }

    /**
     * Метод, который запрашивает ввод параметра hairColor
     * @return
     */
    public String askHairColor(){
        String hairColor = null;
        System.out.printf("Value of hairColor(%s, %s, %s, %s, %s): ", HairColor.BLACK, HairColor.WHITE, HairColor.BLUE,
                HairColor.GREEN, HairColor.YELLOW);
        try {
            return reader.readLine().toUpperCase().trim();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
        }
        return hairColor;
    }

    /**
     * Метод, который запрашивает ввод параметра location.x
     * @return
     */
    public float askLocationX(){
        System.out.print("Value of location.x: ");
        float locationX;
        while (true) {
            try {
                locationX = Float.parseFloat(reader.readLine().trim());
                break;
            } catch (NumberFormatException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Value of location.x must be float");
                System.out.print("Value of location.x: ");
            } catch (IOException e) {
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println(e.getMessage());
            }
        }
        return locationX;
    }

    /**
     * Метод, который запрашивает ввод параметра location.y
     * @return
     */
    public long askLocationY(){
        System.out.print("Value of location.y: ");
        long locationY;
        while (true) {
            try {
                locationY = Long.parseLong(reader.readLine().trim());
                break;
            } catch (NumberFormatException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Value of location.y must be long");
                System.out.print("Value of location.y: ");
            } catch (IOException e) {
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println(e.getMessage());
            }
        }
        return locationY;
    }

    /**
     * Метод, который запрашивает ввод параметра location.name
     * @return
     */
    public String askLocationName(){
        System.out.print("Value of location.name: ");
        String locationName = null;
        try {
            locationName = reader.readLine().trim();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
        }
        return locationName;
    }
}
