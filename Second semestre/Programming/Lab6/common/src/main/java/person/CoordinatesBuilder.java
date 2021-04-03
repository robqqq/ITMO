package person;

import exceptions.InvalidCoordinatesXException;
import exceptions.InvalidCoordinatesYException;

public interface CoordinatesBuilder {

    void setX(Double x) throws InvalidCoordinatesXException;

    void setY(long y) throws InvalidCoordinatesYException;

    Coordinates getCoordinates();
}
