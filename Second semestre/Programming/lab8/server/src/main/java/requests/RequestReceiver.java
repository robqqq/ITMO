package requests;

import networkMessages.Request;

import java.io.IOException;
import java.net.SocketAddress;

public interface RequestReceiver {

    Request receiveRequest() throws IOException, ClassNotFoundException;

    SocketAddress getAddress();
}
