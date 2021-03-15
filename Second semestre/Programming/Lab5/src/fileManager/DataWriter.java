package fileManager;

import person.Person;

import java.util.Collection;

public interface DataWriter {
    void writeElements(Collection<? extends Person> collection);
}
