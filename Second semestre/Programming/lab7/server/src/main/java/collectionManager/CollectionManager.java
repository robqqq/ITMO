package collectionManager;

import person.Person;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Stream;

public interface CollectionManager {


    Class getType();

    int getSize();

    LocalDate getInitDate();

    void addElement(Person person);

    void removeElement(int id);

    void clear();

    void setCollection(Collection<Person> persons);

    Stream<Person> getPersonStream();

}
