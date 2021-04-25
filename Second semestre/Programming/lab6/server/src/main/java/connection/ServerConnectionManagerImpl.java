package connection;

import log.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class ServerConnectionManagerImpl implements ServerConnectionManager {
    private DatagramChannel channel;

    @Override
    public DatagramChannel openConnection(int port) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        channel = DatagramChannel.open();
        channel.bind(socketAddress);
        //channel.configureBlocking(false);
        Log.log().info("server has opened connection on port {}", port);
        return channel;
    }

    @Override
    public void closeConnection() throws IOException{
        channel.socket().close();
        channel.close();
    }
}
