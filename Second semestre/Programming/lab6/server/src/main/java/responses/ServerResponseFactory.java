package responses;

import networkMessages.Response;
import networkMessages.ResponseImpl;
import networkMessages.ResponseType;

public class ServerResponseFactory implements ResponseFactory{

    @Override
    public Response createDefaultResponse(String content) {
        return new ResponseImpl(ResponseType.DEFAULT_RESPONSE, content);
    }

    @Override
    public Response createErrorResponse(String content) {
        return new ResponseImpl(ResponseType.ERROR_RESPONSE, content);
    }

    @Override
    public Response createNeedObjectResponse() {
        return new ResponseImpl(ResponseType.NEED_OBJECT_RESPONSE, null);
    }

    @Override
    public Response createClientCommandResponse() {
        return new ResponseImpl(ResponseType.CLIENT_COMMAND_RESPONSE, null);
    }
}
