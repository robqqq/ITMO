package serverCommands;

import java.util.Locale;
import java.util.Stack;

/**
 * Класс команды, которая выводит последние 9 команд
 */
public class HistoryCommand implements ServerCommand{
    private final Stack<String> history;
    private Locale locale;

    /**
     * @param history история использованных команд
     */
    public HistoryCommand(Stack<String> history){
        this.history = history;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        history.forEach(command -> stringBuilder.append(command).append("\n"));
        return stringBuilder.toString();
    }
}
