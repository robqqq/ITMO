package dbManager;

import person.RawPerson;

public interface DataWriter {
    
    void addElement(RawPerson person);

    void updateElement(RawPerson person, int id);

    void clearElements();

    void removeElement(int id);

    void addUser(String username, String passwordHash);
}
