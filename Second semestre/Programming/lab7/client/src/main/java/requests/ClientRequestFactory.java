package requests;

import auth.Auth;
import networkMessages.Request;
import networkMessages.RequestImpl;
import networkMessages.RequestType;
import person.RawPerson;

public class ClientRequestFactory implements RequestFactory {

    @Override
    public Request createArgObjectRequest(String command, String arg, RawPerson person, Auth auth) {
        return new RequestImpl(RequestType.ARG_OBJECT_REQUEST, command, arg, person, auth);
    }

    @Override
    public Request createObjectRequest(String command, RawPerson person, Auth auth) {
        return new RequestImpl(RequestType.OBJECT_REQUEST, command, null, person, auth);
    }

    @Override
    public Request createArgRequest(String command, String arg, Auth auth) {
        return new RequestImpl(RequestType.ARG_REQUEST, command, arg, null, auth);
    }

    @Override
    public Request createSimpleRequest(String command, Auth auth) {
        return new RequestImpl(RequestType.SIMPLE_REQUEST, command, null, null, auth);
    }

    @Override
    public Request createAuthRegRequest(String command, Auth auth){
        return new RequestImpl(RequestType.AUTH_REG_COMMAND, command, null, null, auth);
    }
}
