package commands;

/**
 * Класс команды, которая завершает программу
 */
public class ExitCommand implements Command{

    /**
     * Метод, который запускает команду
     * @param args
     */
    @Override
    public void execute(String[] args) {
        System.exit(0);
    }
}
