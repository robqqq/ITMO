package networkMessages;

import auth.Auth;
import person.RawPerson;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class RequestImpl implements Request, Serializable {
    private static final long serialVersionUID = 8785774135702639006L;
    private final RequestType type;
    private final String command;
    private final String arg;
    private final RawPerson person;
    private final Auth auth;
    private final Locale locale;

    public RequestImpl(RequestType type, String command, String arg, RawPerson person, Auth auth, Locale locale) {
        this.type = type;
        this.command = command;
        this.arg = arg;
        this.person = person;
        this.auth = auth;
        this.locale = locale;
    }

    @Override
    public RequestType getType() {
        return type;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getArg() {
        return arg;
    }

    @Override
    public RawPerson getObject() {
        return person;
    }

    @Override
    public Auth getAuth(){
        return auth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestImpl request = (RequestImpl) o;
        return type == request.type && command.equals(request.command) && Objects.equals(arg, request.arg) && Objects.equals(person, request.person) && auth.equals(request.auth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, command, arg, person, auth);
    }

    @Override
    public String toString() {
        return "RequestImpl{" +
                "type=" + type +
                ", command='" + command + '\'' +
                ", arg='" + arg + '\'' +
                ", person=" + person +
                ", auth=" + auth +
                '}';
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
