package dataManager;

import auth.Auth;
import person.Person;
import person.RawPerson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

public class DBManager implements DataManager {
    private final DataWriter dataWriter;
    private final DataReader dataReader;
    
    public DBManager(String url, String user, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        this.dataReader = new DBReader(connection);
        this.dataWriter = new DBWriter(connection);
    }

    @Override
    public Collection<Person> readElements(){
        return dataReader.readElements();
    }

    @Override
    public Person addElement(RawPerson rawPerson, Auth auth) {
        dataWriter.addElement(rawPerson, auth);
        return dataReader.getLastElement();
    }

    @Override
    public Person updateElement(RawPerson rawPerson, int id, Auth auth) {
        dataWriter.updateElement(rawPerson, id, auth);
        return dataReader.getElement(id);
    }

    @Override
    public void clearElements(Auth auth) {
        dataWriter.clearElements(auth);
    }

    @Override
    public void removeElement(int id, Auth auth) {
        dataWriter.removeElement(id, auth);
    }

    @Override
    public void addUser(Auth auth) {
        dataWriter.addUser(auth);
    }

    @Override
    public Set<Auth> readUsers() {
        return dataReader.readUsers();
    }
}
