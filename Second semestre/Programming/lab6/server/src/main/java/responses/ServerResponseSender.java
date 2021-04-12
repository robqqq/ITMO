package responses;

import networkMessages.Response;

import java.io.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerResponseSender implements ResponseSender{
    private final DatagramChannel channel;
    private final SocketAddress address;
    private final ByteArrayOutputStream byteArrayOutputStream;
    private static final Logger logger = LoggerFactory.getLogger(ServerResponseSender.class);

    public ServerResponseSender(DatagramChannel channel, SocketAddress address){
        this.channel = channel;
        this.address = address;
        byteArrayOutputStream = new ByteArrayOutputStream(4096);
    }

    public void sendResponse(Response response){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(response);
            channel.send(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()), address);
        } catch (IOException e) {
            logger.error("response sending error", e);
        }
    }
}
