package networkMessages;

import person.RawPerson;

import java.io.Serializable;
import java.util.Objects;

public class RequestImpl implements Request, Serializable {
    private static final long serialVersionUID = 8785774135702639006L;
    private final RequestType type;
    private final String command;
    private final String arg;
    private final RawPerson person;

    public RequestImpl(RequestType type, String command, String arg, RawPerson person) {
        this.type = type;
        this.command = command;
        this.arg = arg;
        this.person = person;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestImpl request = (RequestImpl) o;
        return type == request.type && command.equals(request.command) && Objects.equals(arg, request.arg)
                && Objects.equals(person, request.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, command, arg, person);
    }

    @Override
    public String toString() {
        return String.format("RequestImpl(type=%s, command=%s, arg=%s, person=%s)", type, command, arg, person);
    }
}
