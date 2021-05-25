package input;

import person.RawPerson;

import java.io.IOException;

public interface PersonReader {

    RawPerson readPerson() throws IOException;
}
