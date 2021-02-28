package commands;

import cleint.ClientManagerInterface;

import java.util.Stack;

/**
 * Класс команды, которая выводит последние 9 команд
 */
public class HistoryCommand implements Command{
    private Stack<String> history;
    private final String description;

    /**
     * Конструктор
     * @param history
     */
    HistoryCommand(Stack<String> history){
        this.history = history;
        description = "вывести последние 9 команд (без их аргументов)";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println(history.get(i));
        }
    }

    @Override
    public String getHelp() {
        return String.format(": %s", description);
    }
}
