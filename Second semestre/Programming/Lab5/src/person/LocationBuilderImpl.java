package person;

import exceptions.InvalidFieldException;
import messages.Messenger;

/**
 * Реализация интерфеса LocationBuilder
 */
public class LocationBuilderImpl implements LocationBuilder{
    private float x;
    private long y;
    private String name; //Длина строки не должна быть больше 480, Поле может быть null
    private LocationValidator validator;
    private Messenger messenger;

    /**
     * @param messenger мессенджер
     */
    public LocationBuilderImpl(Messenger messenger){
        validator = new LocationValidatorImpl();
        this.messenger = messenger;
    }

    @Override
    public void setName(String name) throws InvalidFieldException {
        if (validator.validateLocationName(name)){
            this.name = name;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidLocationName"));
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
