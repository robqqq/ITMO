package commands;

import application.Application;

/**
 * Класс команды, которая завершает программу
 */
public class ExitCommand implements Command{
    private Application app;

    /**
     * @param app объект Application
     */
    public ExitCommand(Application app){
        this.app = app;
    }

    @Override
    public void execute() {
        app.exit();
    }
}
