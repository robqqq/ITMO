package person;

import exceptions.InvalidFieldException;

public interface LocationBuilder {

    void setX(float x);

    void setY(long y);

    void setName(String name) throws InvalidFieldException;

    Location getLocation();
}
