package commands;

import exceptions.NoSuchCommandException;
import messages.Messenger;
import output.OutputManager;

import java.util.Set;

/**
 * Класс команды, которая выводит справку по доступным командам
 */
public class HelpCommand implements Command{
    private Set<String> commands;
    private Messenger messenger;
    private OutputManager outputManager;

    /**
     * @param commands set имен команд
     * @param messenger мессенджер
     * @param outputManager менеджер вывода
     */
    public HelpCommand(Set<String> commands, Messenger messenger, OutputManager outputManager){
        this.commands = commands;
        this.messenger = messenger;
        this.outputManager = outputManager;
    }

    @Override
    public void execute() {
        for (String commandName: commands){
            try {
                outputManager.printMsg(messenger.getCommandDescription(commandName) + "\n");
            } catch (NoSuchCommandException e) {
                outputManager.printErrorMsg(e.getMessage() + "\n");
            }
        }
    }
}
