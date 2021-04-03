package inputManager;

import person.Person;
import person.RawPerson;

import java.io.IOException;
import java.time.LocalDateTime;

public interface PersonReader {

    RawPerson readPerson() throws IOException;
}
