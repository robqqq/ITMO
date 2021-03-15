package person;

import exceptions.InvalidFieldException;

public interface CoordinatesBuilder {

    void setX(Double x) throws InvalidFieldException;

    void setY(long y) throws InvalidFieldException;

    Coordinates getCoordinates();
}
