package person;

import exceptions.InvalidLocationNameException;

/**
 * Реализация интерфеса LocationBuilder
 */
public class LocationBuilderImpl implements LocationBuilder{
    private float x;
    private long y;
    private String name; //Длина строки не должна быть больше 480, Поле может быть null
    private final LocationValidator validator;

    public LocationBuilderImpl(){
        validator = new LocationValidatorImpl();
    }

    @Override
    public void setName(String name) throws InvalidLocationNameException {
        if (validator.validateLocationName(name)){
            this.name = name;
        } else {
            throw new InvalidLocationNameException();
        }
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(long y) {
        this.y = y;
    }

    @Override
    public Location getLocation(){
        return new Location(x, y, name);
    }
}
