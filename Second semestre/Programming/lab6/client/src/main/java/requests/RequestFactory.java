package requests;

import networkMessages.Request;
import person.Person;
import person.RawPerson;

public interface RequestFactory {

    Request createArgObjectRequest(String command, String arg, RawPerson person);

    Request createObjectRequest(String command, RawPerson person);

    Request createArgRequest(String command, String arg);

    Request createSimpleRequest(String command);
}
