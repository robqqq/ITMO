package person;

import exceptions.InvalidLocationNameException;

public interface LocationBuilder {

    void setX(float x);

    void setY(long y);

    void setName(String name) throws InvalidLocationNameException;

    Location getLocation();
}
