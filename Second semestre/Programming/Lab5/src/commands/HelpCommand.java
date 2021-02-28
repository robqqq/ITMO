package commands;

import cleint.ClientManagerInterface;

import java.util.Iterator;
import java.util.Map;

/**
 * Класс команды, которая выводит справку по доступным командам
 */
public class HelpCommand implements Command{
    private Map<String, Command> commandMap;
    private final String description;

    HelpCommand(Map<String, Command> commandMap){
        this.commandMap = commandMap;
        description = "вывести справку по доступным командам";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        Iterator<String> keyIterator = commandMap.keySet().iterator();
        while (keyIterator.hasNext()){
            String command = keyIterator.next();
            System.out.println(command + " " + commandMap.get(command).getHelp());
        }
    }

    @Override
    public String getHelp() {
        return String.format(": %s", description);
    }
}
