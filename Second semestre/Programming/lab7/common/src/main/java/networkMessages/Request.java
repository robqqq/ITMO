package networkMessages;

import auth.Auth;
import person.Person;
import person.RawPerson;

public interface Request {

    RequestType getType();

    String getCommand();

    String getArg();

    RawPerson getObject();

    Auth getAuth();
}
