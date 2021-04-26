package input;

import auth.Auth;
import person.RawPerson;

import java.io.IOException;

public interface InputManager {

    boolean ready() throws IOException;

    String readCommand() throws IOException;

    RawPerson readPerson() throws IOException;

    Auth readAuth() throws IOException;
}
