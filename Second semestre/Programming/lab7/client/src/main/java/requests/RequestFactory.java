package requests;

import auth.Auth;
import networkMessages.Request;
import person.Person;
import person.RawPerson;

public interface RequestFactory {

    Request createArgObjectRequest(String command, String arg, RawPerson person, Auth auth);

    Request createObjectRequest(String command, RawPerson person, Auth auth);

    Request createArgRequest(String command, String arg, Auth auth);

    Request createSimpleRequest(String command, Auth auth);

    Request createAuthRegRequest(String command, Auth auth);
}
