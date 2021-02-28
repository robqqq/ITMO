package commands;

import cleint.ClientManagerInterface;

/**
 * Общий интерфейс всех классов команд
 */
public interface Command {

    /**
     * Метод, который запускает команду
     * @param args
     */
    void execute(String[] args, ClientManagerInterface clientManager);

    String getHelp();
}
