package responses;

import networkMessages.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class ClientResponseReceiver implements ResponseReceiver{
    private final DatagramSocket socket;

    public ClientResponseReceiver(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public Response receiveResponse() throws IOException {
        ByteBuffer bytes = ByteBuffer.allocate(65536);
        DatagramPacket receivePacket = new DatagramPacket(bytes.array(), bytes.array().length);
        socket.receive(receivePacket);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes.array()));
        try {
            return (Response) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException();
        }
    }
}
