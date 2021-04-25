package server;

import application.Application;
import log.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Main {

    public static void main(String[] args){
//        Log.log().info("Program has started");
//        SocketAddress address = new InetSocketAddress(61231);
//        byte[] b = new byte[10];
//        DatagramChannel channel;
//        try{
//            channel = DatagramChannel.open();
//            channel.bind(address);
//            ByteBuffer buffer = ByteBuffer.wrap(b);
//            buffer.clear();
//            address = channel.receive(buffer);
//            for (int i = 0; i < 10; i++) {
//                b[i] *= 2;
//            }
//            buffer.flip();
//            channel.send(buffer, address);
//        } catch (IOException e) {
//            Log.log().error("exception while opening channel", e);
//        }
        Application app = new ServerApplication(61231);
        app.start();
    }
}
