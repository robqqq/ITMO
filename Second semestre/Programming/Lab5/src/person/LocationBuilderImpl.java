package person;

import exceptions.InvalidFieldException;
import messages.Messenger;

public class LocationBuilderImpl implements LocationBuilder{
    private float x;
    private long y;
    private String name; //Длина строки не должна быть больше 480, Поле может быть null
    private LocationValidator validator;
    private Messenger messenger;

    public LocationBuilderImpl(Messenger messenger){
        validator = new LocationValidatorImpl();
        this.messenger = messenger;
    }

    /**
     * Метод, который устанавливает название локации
     * @param name
     * @throws InvalidFieldException
     */
    public void setName(String name) throws InvalidFieldException {
        if (validator.validateLocationName(name)){
            this.name = name;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidLocationName"));
        }
    }

    /**
     * Метод, который устанавливает значение X локации
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Метод, который устанавливает значение Y локации
     * @param y
     */
    public void setY(long y) {
        this.y = y;
    }

    public Location getLocation(){
        return new Location(x, y, name);
    }
}
