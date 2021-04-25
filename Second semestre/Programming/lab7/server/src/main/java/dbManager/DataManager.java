package dbManager;

import auth.Auth;
import person.Person;
import person.RawPerson;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface DataManager {
    
    Collection<Person> readElements();
    
    Person addElement(RawPerson rawPerson);

    Person updateElement(RawPerson rawPerson, int id);

    void clearElements();

    void removeElement(int id);

    void addUser(Auth auth);

    Set<Auth> readUsers();
}
