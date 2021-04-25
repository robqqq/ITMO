package serverCommands;

import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;
import exceptions.NoSuchCommandException;
import networkMessages.RequestType;
import person.RawPerson;
import auth.Auth;

import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public interface ServerCommandManager {

    /**
     * Метод, который запускает команду
     * @param command имя команды
     * @param arg аргумент команды
     * @throws NoSuchCommandException если такой команды не существует
     */
    void executeClientCommand(RequestType type, String command, String arg, RawPerson person, Auth auth, SocketAddress address, DatagramChannel channel);

    void executeServerCommand(String command);
}
