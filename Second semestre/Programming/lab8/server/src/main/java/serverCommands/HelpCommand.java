package serverCommands;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelpCommand implements ServerCommand{
    private Locale locale;

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String execute() {
        return ResourceBundle.getBundle("messages", locale).getString("menu_item.help") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.help") +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("menu_item.info") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.info") +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("menu_item.add") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.add") +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("menu_item.update") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.update") +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("menu_item.remove") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.remove") +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("menu_item.clear") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.clear") +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("menu_item.execute_script") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.execute_script") +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("menu_item.add_if_max") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.add_if_max") +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("menu_item.add_if_min") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.add_if_min") +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("menu_item.history") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.history") +
                "\n" +
                ResourceBundle.getBundle("messages", locale).getString("menu_item.max_by_eye_color") +
                " : " +
                ResourceBundle.getBundle("messages", locale).getString("command_output.help.max_by_eye_color") +
                "\n";
    }
}
