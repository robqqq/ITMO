package dataManager;

import auth.Auth;
import exceptions.DBException;
import exceptions.NoSuchIdException;
import person.RawPerson;

import java.sql.*;
import java.time.LocalDate;

public class DBWriter implements DataWriter{
    private final Connection connection;

    public DBWriter(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addElement(RawPerson person, Auth auth) {
        try (PreparedStatement stm = connection.prepareStatement(
                "insert into persons (id, name, coordinates_x, coordinates_y, height, birthday, eyecolor, " +
                        "haircolor, location_x, location_y, location_name, creationdate, owner) values " +
                        "(nextval('id_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
            setPersonToStatement(person, stm);
            stm.setDate(11, Date.valueOf(LocalDate.now()));
            stm.setString(12, auth.getLogin());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateElement(RawPerson person, int id, Auth auth) {
        try (PreparedStatement stm = connection.prepareStatement("update persons set " +
                "name = ?," +
                "coordinates_x = ?," +
                "coordinates_y = ?," +
                "height = ?," +
                "birthday = ?," +
                "eyecolor = ?," +
                "haircolor = ?," +
                "location_x = ?," +
                "location_y = ?," +
                "location_name = ?" +
                "where id = ? and owner = ?")){
            setPersonToStatement(person, stm);
            stm.setInt(11 ,id);
            stm.setString(12, auth.getLogin());
            if (stm.executeUpdate() < 1){
                throw new NoSuchIdException();
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void clearElements(Auth auth) {
        try (PreparedStatement stm = connection.prepareStatement("delete from persons where owner = ?")){
            stm.setString(1, auth.getLogin());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void removeElement(int id, Auth auth) {
        try (PreparedStatement stm = connection.prepareStatement("delete from persons where id = ? and owner = ?")){
            stm.setInt(1, id);
            stm.setString(2, auth.getLogin());
            if (stm.executeUpdate() < 1){
                throw new NoSuchIdException();
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(Auth auth) {
        try (PreparedStatement stm = connection.prepareStatement("insert into users (username, password) values (?, ?)")){
            stm.setString(1, auth.getLogin());
            stm.setString(2, auth.getPassword());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private void setPersonToStatement(RawPerson person, PreparedStatement stm) throws SQLException {
        stm.setString(1, person.getName());
        stm.setDouble(2, person.getCoordinates().getX());
        stm.setLong(3, person.getCoordinates().getY());
        stm.setLong(4, person.getHeight());
        stm.setDate(5, Date.valueOf(person.getBirthday().toLocalDate()));
        stm.setString(6, person.getEyeColor().toString());
        stm.setString(7, person.getHairColor().toString());
        stm.setFloat(8, person.getLocation().getX());
        stm.setLong(9, person.getLocation().getY());
        if (person.getLocation().getName() == null){
            stm.setNull(10, Types.VARCHAR);
        } else {
            stm.setString(10, person.getLocation().getName());
        }
    }
}
