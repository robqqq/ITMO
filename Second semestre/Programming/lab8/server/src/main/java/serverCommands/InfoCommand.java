package serverCommands;

import collectionManager.CollectionManager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Класс команды, которая выводит в стандартный поток вывода информацию о коллекции
 */
public class InfoCommand implements ServerCommand{
    private final CollectionManager collectionManager;
    private Locale locale;

    /**
     * @param collectionManager менеджер коллекции
     */
    public InfoCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String execute() {
        return ResourceBundle.getBundle("messages", locale).getString("command_output.info.type") +
                ": " +
                collectionManager.getType().getSimpleName() +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("command_output.info.init_date") +
                ": " +
                collectionManager.getInitDate() +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("command_output.info.size") +
                ": " +
                collectionManager.getSize();
    }
}
