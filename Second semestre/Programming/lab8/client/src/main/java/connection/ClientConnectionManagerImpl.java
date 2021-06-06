package connection;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ClientConnectionManagerImpl implements ClientConnectionManager {
    private DatagramSocket socket;
    private SocketAddress address;

    @Override
    public DatagramSocket openConnection(String address, int port) throws IOException {
        this.address = new InetSocketAddress(address, port);
        socket = new DatagramSocket();
        return socket;
    }

    @Override
    public SocketAddress getSocketAddress(){
        return address;
    }

    @Override
    public void closeConnection(){
        socket.close();
    }
}
