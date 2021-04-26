package serverCommands;

import networkMessages.RequestType;
import networkMessages.Response;
import person.RawPerson;
import auth.Auth;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public interface ServerCommandManager {

    /**
     * Метод, который запускает команду
     * @param command имя команды
     * @param arg аргумент команды
     * @return
     */
    Response executeClientCommand(RequestType type, String command, String arg, RawPerson person, Auth auth);

    void executeServerCommand(String command);
}
