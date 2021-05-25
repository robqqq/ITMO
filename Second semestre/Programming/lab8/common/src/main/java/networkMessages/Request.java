package networkMessages;

import auth.Auth;
import person.RawPerson;

import java.util.Locale;

public interface Request {

    RequestType getType();

    String getCommand();

    String getArg();

    RawPerson getObject();

    Auth getAuth();

    Locale getLocale();
}
