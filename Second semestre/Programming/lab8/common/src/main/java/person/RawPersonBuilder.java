package person;

import exceptions.*;

import java.time.LocalDateTime;

public interface RawPersonBuilder {

    void setName(String name) throws InvalidNameException;

    void setCoordinatesX(Double x) throws InvalidCoordinatesXException;

    void setCoordinatesY(long y) throws InvalidCoordinatesYException;

    void setHeight(long height) throws InvalidHeightException;

    void setBirthday(LocalDateTime birthday) throws InvalidBirthdayException;

    void setEyeColor(EyeColor eyeColor) throws InvalidEyeColorException;

    void setHairColor(HairColor hairColor) throws InvalidHairColorException;

    void setLocationX(float x);

    void setLocationY(long y);

    void setLocationName(String name) throws InvalidLocationNameException;

    RawPerson getRawPerson();
}
