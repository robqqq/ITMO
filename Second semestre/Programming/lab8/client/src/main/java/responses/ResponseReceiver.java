package responses;

import networkMessages.Response;

import java.io.IOException;

public interface ResponseReceiver {

    Response receiveResponse() throws IOException;
}
