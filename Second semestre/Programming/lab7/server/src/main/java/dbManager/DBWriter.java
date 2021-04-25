package dbManager;

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
    public void addElement(RawPerson person) {
        try (PreparedStatement stm = connection.prepareStatement(
                "insert into persons (id, name, coordinates_x, coordinates_y, height, birthday, eyecolor, " +
                        "haircolor, location_x, location_y, location_name, creationdate) values " +
                        "(nextval('id_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")){
            setPersonToStatement(person, stm);
            stm.setDate(11, Date.valueOf(LocalDate.now()));
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateElement(RawPerson person, int id) {
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
                "where id = ?")){
            setPersonToStatement(person, stm);
            stm.setInt(11 ,id);
            if (stm.executeUpdate() < 1){
                throw new NoSuchIdException();
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void clearElements() {
        try (Statement stm = connection.createStatement()){
            stm.executeUpdate("delete from persons");
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void removeElement(int id) {
        try (Statement stm = connection.createStatement()){
            if (stm.executeUpdate("delete from persons where id = " + id) < 1){
                throw new NoSuchIdException();
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(String username, String passwordHash) {
        try (PreparedStatement stm = connection.prepareStatement("insert into users (username, password) values (?, ?)")){
            stm.setString(1, username);
            stm.setString(2, passwordHash);
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
