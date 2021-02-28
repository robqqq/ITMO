package main;

import exceptions.InvalidArgumentException;
import exceptions.NoIdLeftException;
import exceptions.NotUniqueIdException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PersonBuilderInterface {
    void removeId(int id);

    void removeAllId();

    void setId() throws NoIdLeftException;
    void setId(int id) throws InvalidArgumentException;
    void setName(String name) throws InvalidArgumentException;
    void setCoordinates(Coordinates coordinates) throws InvalidArgumentException;
    void setCoordinatesX(Double x) throws InvalidArgumentException;
    void setCoordinatesY(long y) throws InvalidArgumentException;
    void setCreationDate(LocalDate creationDate);
    void setHeight(long height) throws InvalidArgumentException;
    void setBirthday(LocalDateTime birthday) throws InvalidArgumentException;
    void setEyeColor(EyeColor eyeColor) throws InvalidArgumentException;
    void setHairColor(HairColor hairColor) throws InvalidArgumentException;
    void setLocation(Location location) throws InvalidArgumentException;
    void setLocationX(float x);
    void setLocationY(long y);
    void setLocationName(String name) throws InvalidArgumentException;
    Person getPerson()  throws InvalidArgumentException ;
}
