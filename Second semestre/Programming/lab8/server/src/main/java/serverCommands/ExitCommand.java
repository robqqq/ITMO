package serverCommands;

import application.Application;

import java.util.Locale;

/**
 * Класс команды, которая завершает программу
 */
public class ExitCommand implements ServerCommand{
    private final Application app;
    private Locale locale;

    /**
     * @param app объект Application
     */
    public ExitCommand(Application app){
        this.app = app;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String execute() {
        app.exit();
        return "";
    }
}
