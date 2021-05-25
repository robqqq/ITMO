package collectionManager;

import person.Person;

import java.util.Collection;
import java.util.stream.Stream;

public interface ClientCollectionManager {

    int size();

    int fieldCount();

    Stream<Person> getPersonStream();

    void setPersons(Collection<Person> personCollection);

    Object getField(int rawIndex, int columnIndex);
}
