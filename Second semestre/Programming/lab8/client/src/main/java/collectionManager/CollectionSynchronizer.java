package collectionManager;

import auth.Auth;
import clientCommands.ClientCommandManager;
import networkMessages.Response;
import networkMessages.ResponseType;
import requests.ClientRequestFactory;
import requests.ClientRequestSender;
import requests.RequestFactory;
import requests.RequestSender;
import responses.ClientResponseReceiver;
import responses.ResponseReceiver;

import java.io.IOException;

public class CollectionSynchronizer {
    private ClientCommandManager commandManager;

    public CollectionSynchronizer(ClientCommandManager commandManager){
        this.commandManager = commandManager;
    }

    public void synchronizeCollection(ClientCollectionManager clientCollectionManager) throws IOException {
        Response response = commandManager.executeCommand("show", null, null);
        if (response.getType() == ResponseType.DEFAULT_RESPONSE){
            clientCollectionManager.setPersons(response.getPersonCollection());
        }
    }
}
