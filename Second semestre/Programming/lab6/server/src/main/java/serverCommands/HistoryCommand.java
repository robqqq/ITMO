package serverCommands;

import messages.Messenger;

import java.util.Stack;

/**
 * Класс команды, которая выводит последние 9 команд
 */
public class HistoryCommand implements ServerCommand{
    private final Stack<String> history;
    private final Messenger messenger;

    /**
     * @param history история использованных команд
     */
    public HistoryCommand(Stack<String> history, Messenger messenger){
        this.history = history;
        this.messenger = messenger;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messenger.getMsg("historyOutput"))
                .append(":\n");
        history.forEach(command -> stringBuilder.append(command).append("\n"));
        return stringBuilder.toString();
    }
}
