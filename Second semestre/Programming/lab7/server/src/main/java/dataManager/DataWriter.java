package dataManager;

import auth.Auth;
import person.RawPerson;

public interface DataWriter {
    
    void addElement(RawPerson person, Auth auth);

    void updateElement(RawPerson person, int id, Auth auth);

    void clearElements(Auth auth);

    void removeElement(int id, Auth auth);

    void addUser(Auth auth);
}
