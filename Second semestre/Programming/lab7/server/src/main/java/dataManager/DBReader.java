package dataManager;

import auth.Auth;
import exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DBReader implements DataReader{
    private final Connection connection;
    private final Logger logger = LoggerFactory.getLogger("DBReader.class");

    public DBReader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Person> readElements(){
        Collection<Person> persons = new TreeSet<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from persons");
            while(rs.next()){
                persons.add(createPerson(rs));
            }
        } catch (SQLException e) {
            logger.error("DB problems", e);
            throw new DBException(e);
        }
        return persons;
    }

    @Override
    public Set<Auth> readUsers() {
        Set<Auth> users = new HashSet<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from users");
            while(rs.next()){
                users.add(new Auth(rs.getString("username"), rs.getString("password")));
            }
            return users;
        } catch (SQLException e) {
            logger.error("DB problems", e);
            throw new DBException(e);
        }
    }

    @Override
    public Person getElement(int id) {
        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery("select * from persons where id = " + id);
            if (rs.next()) {
                return createPerson(rs);
            } else {
                throw new NoSuchIdException();
            }

        } catch (SQLException  e){
            logger.error("DB problems", e);
            throw new DBException(e);
        }
    }

    @Override
    public Person getLastElement(){
        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery("select * from persons where id = (select max(id) from persons)");
            if (rs.next()) {
                return createPerson(rs);
            } else {
                return null;
            }
        } catch (SQLException  e){
            logger.error("DB problems", e);
            throw new DBException(e);
        }
    }

    private Person createPerson(ResultSet rs) throws SQLException, InvalidFieldException {
        RawPersonBuilder personBuilder = new RawPersonBuilderImpl();
        personBuilder.setName(rs.getString("name"));
        personBuilder.setCoordinatesX(rs.getDouble("coordinates_x"));
        personBuilder.setCoordinatesY(rs.getLong("coordinates_y"));
        personBuilder.setHeight(rs.getLong("height"));
        personBuilder.setBirthday(rs.getDate("birthday").toLocalDate().atStartOfDay());
        personBuilder.setEyeColor(EyeColor.valueOf(rs.getString("eyecolor")));
        personBuilder.setHairColor(HairColor.valueOf(rs.getString("haircolor")));
        personBuilder.setLocationX(rs.getFloat("location_x"));
        personBuilder.setLocationY(rs.getLong("location_y"));
        personBuilder.setLocationName(rs.getString("location_name"));
        return new Person(rs.getInt("id"), personBuilder.getRawPerson(),
                rs.getDate("creationdate").toLocalDate().atStartOfDay());
    }
}
