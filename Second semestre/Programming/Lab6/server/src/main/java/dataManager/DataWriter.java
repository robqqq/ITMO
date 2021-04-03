package dataManager;

import person.Person;

import java.util.Collection;

public interface DataWriter {
    
    void writeElements(Collection<Person> collection);
}
