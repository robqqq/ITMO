package commands;

import application.Application;

/**
 * Класс команды, которая завершает программу
 */
public class ExitCommand implements Command{
    private Application app;

    public ExitCommand(Application app){
        this.app = app;
    }

    /**
     * Метод, который запускает команду
     */
    @Override
    public void execute() {
        app.exit();
    }
}
