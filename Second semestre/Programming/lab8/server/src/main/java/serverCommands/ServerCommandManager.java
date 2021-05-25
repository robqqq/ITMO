package serverCommands;

import auth.Auth;
import networkMessages.RequestType;
import networkMessages.Response;
import person.RawPerson;

import java.util.Locale;

public interface ServerCommandManager {

    /**
     * Метод, который запускает команду
     * @param command имя команды
     * @param arg аргумент команды
     * @return
     */
    Response executeClientCommand(RequestType type, String command, String arg, RawPerson person, Auth auth, Locale locale);

    void executeServerCommand(String command);
}
