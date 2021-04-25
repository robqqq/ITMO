package requests;

import networkMessages.Request;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerRequestReceiver implements RequestReceiver {
    private final DatagramChannel channel;
    private final ByteBuffer buf;
    private SocketAddress address;


    public ServerRequestReceiver(DatagramChannel channel) throws IOException {
        buf = ByteBuffer.allocate(4096);
        this.channel = channel;
    }

    @Override
    public Request receiveRequest() throws IOException, ClassNotFoundException {
        address = channel.receive(buf);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
        return (Request) objectInputStream.readObject();
    }

    @Override
    public SocketAddress getAddress(){
        return address;
    }

}
