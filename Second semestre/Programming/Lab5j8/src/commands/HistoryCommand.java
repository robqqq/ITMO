package commands;

import java.util.Stack;

/**
 * Класс команды, которая выводит последние 9 команд
 */
public class HistoryCommand implements Command{
    private Stack<String> history;

    /**
     * Конструктор
     * @param history
     */
    HistoryCommand(Stack<String> history){
        this.history = history;
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println(history.get(i));
        }
    }
}
