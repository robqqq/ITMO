package requests;

import networkMessages.Request;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class ClientRequestSender implements RequestSender{
    private SocketAddress address;
    private DatagramSocket socket;

    public ClientRequestSender(SocketAddress address, DatagramSocket socket){
        this.address = address;
        this.socket = socket;
    }

    @Override
    public void sendRequest(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        ObjectOutput objOutput = new ObjectOutputStream(byteArrayOutputStream);
        objOutput.writeObject(request);
        DatagramPacket requestPacket = new DatagramPacket(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), address);
        socket.send(requestPacket);
        byteArrayOutputStream.close();
    }
}
