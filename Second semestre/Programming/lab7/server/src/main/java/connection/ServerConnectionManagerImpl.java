package connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConnectionManagerImpl implements ServerConnectionManager {
    private DatagramChannel channel;
    private static final Logger logger = LoggerFactory.getLogger(ServerConnectionManagerImpl.class);

    @Override
    public DatagramChannel openConnection(int port) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        channel = DatagramChannel.open();
        channel.bind(socketAddress);
        logger.info("server has opened connection on port {}", port);
        return channel;
    }

    @Override
    public void closeConnection() throws IOException{
        channel.socket().close();
        channel.close();
    }
}
