package commands;

import cleint.ClientManagerInterface;
import main.ObjectManager;
import java.util.*;

/**
 * Класс, который производит чтение с консоли и отправляет запросы командам
 */
public class CommandManager implements CommandManagerInterface{
    private ObjectManager personManager;
    private Map<String, Command> commandMap;
    private String command;
    private String[] args;
    private Stack<String> history = new Stack<>();
    private Set<String> usedScripts;

    /**
     * Конструктор
     * @param personManager
     */
    public CommandManager(ObjectManager personManager){
        this.personManager = personManager;
        usedScripts = new HashSet<>();
        commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand(commandMap));
        commandMap.put("info", new InfoCommand(personManager));
        commandMap.put("show", new ShowCommand(personManager));
        commandMap.put("add", new AddCommand(personManager));
        commandMap.put("update", new UpdateCommand(personManager));
        commandMap.put("remove_by_id", new RemoveByIdCommand(personManager));
        commandMap.put("clear", new ClearCommand(personManager));
        commandMap.put("save", new SaveCommand(personManager));
        commandMap.put("execute_script", new ExecuteScriptCommand(this));
        commandMap.put("exit", new ExitCommand());
        commandMap.put("add_if_max", new AddIfMaxCommand(personManager));
        commandMap.put("add_if_min", new AddIfMinCommand(personManager));
        commandMap.put("history", new HistoryCommand(history));
        commandMap.put("max_by_eye_color", new MaxByEyeColorCommand(personManager));
        commandMap.put("filter_contains_name", new FilterContainsNameCommand(personManager));
        commandMap.put("print_field_descending_eye_color", new PrintFieldDescendingEyeColorCommand(personManager));
    }

    public void executeCommand(String[] input, ClientManagerInterface clientManager){
        command = input[0].toLowerCase();
        args = new String[input.length - 1];
        for (int i = 0; i < args.length; i++) {
            args[i] = input[i + 1];
        }
        if (commandMap.containsKey(command)) {
            commandMap.get(command).execute(args, clientManager);
            history.push(command);
            if (history.size() > 9) {
                history.remove(0);
            }
        } else {
            System.out.println("Unknown command: " + command);
        }
    }

    /**
     * Метод, который добавляет скрипт в историю использованных скриптов
     * @param scriptName
     */
    public void usedScriptAdd(String scriptName){
        usedScripts.add(scriptName);
    }

    /**
     * Метод, который проверяет наличие скрипта в истории использованных скриптов
     * @param scriptName
     * @return
     */
    public boolean scriptIsUsed(String scriptName){
        return usedScripts.contains(scriptName);
    }

    @Override
    public void clearUsedScripts(){
        usedScripts.clear();
    }
}