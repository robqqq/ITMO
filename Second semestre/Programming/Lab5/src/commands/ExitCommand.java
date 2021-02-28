package commands;

import cleint.ClientManagerInterface;

/**
 * Класс команды, которая завершает программу
 */
public class ExitCommand implements Command{
    private final String description;

    ExitCommand(){
        description = "завершить программу (без сохранения в файл)";
    }

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args, ClientManagerInterface clientManager) {
        clientManager.exit();
    }

    @Override
    public String getHelp() {
        return String.format(": %s", description);
    }
}
