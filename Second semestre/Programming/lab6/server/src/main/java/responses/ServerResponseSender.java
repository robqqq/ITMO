package responses;

import log.Log;
import networkMessages.Response;

import java.io.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerResponseSender implements ResponseSender{
    private DatagramChannel channel;
    private SocketAddress address;
    private ByteArrayOutputStream byteArrayOutputStream;

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
            Log.log().error("response sending error", e);
        }
    }
}
