package responses;

import networkMessages.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class ClientResponseReceiver implements ResponseReceiver{
    private DatagramSocket socket;
    private ByteBuffer bytes;

    public ClientResponseReceiver(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public Response receiveResponse() throws IOException {
        bytes = ByteBuffer.allocate(4096);
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
