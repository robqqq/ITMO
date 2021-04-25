package input;

import auth.Auth;
import person.Person;
import person.RawPerson;

import java.io.IOException;
import java.time.LocalDateTime;

public interface InputManager {

    boolean ready() throws IOException;

    String readCommand() throws IOException;

    RawPerson readPerson() throws IOException;

    Auth readAuth() throws IOException;
}
