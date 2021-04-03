package connection;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public interface ClientConnectionManager {

    DatagramSocket openConnection(String address, int port) throws IOException;

    SocketAddress getSocketAddress();

    void closeConnection();
}

