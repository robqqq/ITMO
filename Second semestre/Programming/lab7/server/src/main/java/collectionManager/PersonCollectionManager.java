package collectionManager;

import person.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Реализация интерфейса CollectionManager
 */
public class PersonCollectionManager implements CollectionManager {
    private final LocalDate initDate;
    private final TreeSet<Person> personTreeSet;
    private static final Logger logger = LoggerFactory.getLogger(PersonCollectionManager.class);

    public PersonCollectionManager() {
        initDate = LocalDate.now();
        personTreeSet = new TreeSet<>();
    }

    @Override
    public Class getType() {
        return personTreeSet.getClass();
    }

    @Override
    public int getSize() {
        return personTreeSet.size();
    }

    @Override
    public LocalDate getInitDate() {
        return initDate;
    }

    @Override
    public void addElement(Person person) {
        personTreeSet.add(person);
    }

    @Override
    public boolean removeElement(int id) {
        for (Person person : personTreeSet) {
            if (person.getId() == id) {
                personTreeSet.remove(person);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        personTreeSet.clear();
    }

    @Override
    public void setCollection(Collection<Person> persons){
        personTreeSet.addAll(persons);
    }

    @Override
    public Stream<Person> getPersonStream() {
        return personTreeSet.stream();
    }
}