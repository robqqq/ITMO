package person;

import exceptions.InvalidFieldException;

import java.time.LocalDateTime;

public interface PersonBuilder {

    void setId();

    void setId(int id) throws InvalidFieldException;

    void setName(String name) throws InvalidFieldException;

    void setCoordinatesX(Double x) throws InvalidFieldException;

    void setCoordinatesY(long y) throws InvalidFieldException;

    void setCreationDate(LocalDateTime creationDate) throws InvalidFieldException;

    void setHeight(long height) throws InvalidFieldException;

    void setBirthday(LocalDateTime birthday) throws InvalidFieldException;

    void setEyeColor(EyeColor eyeColor) throws InvalidFieldException;

    void setHairColor(HairColor hairColor) throws InvalidFieldException;

    void setLocationX(float x);

    void setLocationY(long y);

    void setLocationName(String name) throws InvalidFieldException;

    Person getPerson();
}
