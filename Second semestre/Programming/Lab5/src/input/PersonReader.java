package input;

import exceptions.InvalidFieldException;
import person.Person;

import java.io.IOException;
import java.time.LocalDateTime;

public interface PersonReader {

    Person readPerson();

    Person readPerson(int id, LocalDateTime creationDate);
}
