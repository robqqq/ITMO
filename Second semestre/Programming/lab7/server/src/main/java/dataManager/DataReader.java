package dataManager;

import auth.Auth;
import person.Person;

import java.util.Collection;
import java.util.Set;

public interface DataReader {
    
    Collection<Person> readElements();

    Person getElement(int id);

    Set<Auth> readUsers();

    Person getLastElement();
}
