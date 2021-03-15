package input;

import person.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public interface InputManager {

    boolean ready();

    String readCommand();

    Person readPerson();

    Person readPerson(int id, LocalDateTime creationDate);
}
