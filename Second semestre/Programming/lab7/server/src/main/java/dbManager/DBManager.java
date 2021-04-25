package dbManager;

import auth.Auth;
import person.Person;
import person.RawPerson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
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
    public Person addElement(RawPerson rawPerson) {
        dataWriter.addElement(rawPerson);
        return dataReader.getLastElement();
    }

    @Override
    public Person updateElement(RawPerson rawPerson, int id) {
        dataWriter.updateElement(rawPerson, id);
        return dataReader.getElement(id);
    }

    @Override
    public void clearElements() {
        dataWriter.clearElements();
    }

    @Override
    public void removeElement(int id) {
        dataWriter.removeElement(id);
    }

    @Override
    public void addUser(Auth auth) {
        dataWriter.addUser(auth.getLogin(), auth.getPassword());
    }

    @Override
    public Set<Auth> readUsers() {
        return dataReader.readUsers();
    }
}
