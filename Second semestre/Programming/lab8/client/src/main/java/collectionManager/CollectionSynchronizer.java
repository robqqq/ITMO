package collectionManager;

import authManager.ClientAuthManager;
import networkMessages.Response;
import networkMessages.ResponseType;
import requests.ClientRequestFactory;
import requests.ClientRequestSender;
import requests.RequestFactory;
import requests.RequestSender;
import responses.ClientResponseReceiver;
import responses.ResponseReceiver;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class CollectionSynchronizer {
    private DatagramSocket socket;
    private SocketAddress address;

    public CollectionSynchronizer(DatagramSocket socket, SocketAddress address){
        this.socket = socket;
        this.address = address;
    }

    public void synchronizeCollection(ClientAuthManager authManager) throws IOException {
        RequestFactory requestFactory = new ClientRequestFactory();
        RequestSender requestSender = new ClientRequestSender(address, socket);
        requestSender.sendRequest(requestFactory.createSimpleRequest("show", authManager.getAuth()));
    }
}
