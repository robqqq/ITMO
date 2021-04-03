package collectionManager;

import exceptions.BrokenDataException;
import exceptions.InvalidFieldException;
import exceptions.NoDataException;
import exceptions.NoEnvVarException;
import person.Person;
import person.RawPerson;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface CollectionManager {


    Class getType();

    int getSize();

    LocalDate getInitDate();

    void addElement(Person person);

    void addElement(RawPerson rawPerson);

    boolean removeElement(int id);

    void clear();

    void loadPersons() throws NoEnvVarException, InvalidFieldException, NoDataException, BrokenDataException;

    void savePersons();

    Stream<Person> getPersonStream();

}
