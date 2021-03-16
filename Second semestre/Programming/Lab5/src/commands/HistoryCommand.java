package commands;

import output.OutputManager;

import java.util.Stack;

/**
 * Класс команды, которая выводит последние 9 команд
 */
public class HistoryCommand implements Command{
    private Stack<String> history;
    private OutputManager outputManager;

    /**
     * @param history история использованных команд
     * @param outputManager менеджер вывода
     */
    public HistoryCommand(Stack<String> history, OutputManager outputManager){
        this.history = history;
        this.outputManager = outputManager;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        for (int i = history.size() - 1; i >= 0; i--) {
            outputManager.printMsg(history.get(i) + "\n");
        }
    }
}
