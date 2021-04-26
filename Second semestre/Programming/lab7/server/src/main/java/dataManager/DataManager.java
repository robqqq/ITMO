package dataManager;

import auth.Auth;
import person.Person;
import person.RawPerson;

import java.util.Collection;
import java.util.Set;

public interface DataManager {
    
    Collection<Person> readElements();
    
    Person addElement(RawPerson rawPerson, Auth auth);

    Person updateElement(RawPerson rawPerson, int id, Auth auth);

    void clearElements(Auth auth);

    void removeElement(int id, Auth auth);

    void addUser(Auth auth);

    Set<Auth> readUsers();
}
