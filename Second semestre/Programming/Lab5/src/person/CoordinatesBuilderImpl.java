package person;

import exceptions.InvalidFieldException;
import messages.Messenger;

/**
 * Реализация интерфеса CoordinatesBuilder
 */
public class CoordinatesBuilderImpl implements CoordinatesBuilder{
    private Double x; //Максимальное значение поля: 882, Поле не может быть null
    private long y; //Значение поля должно быть больше -266
    private CoordinatesValidator validator;
    private Messenger messenger;

    /**
     * @param messenger мессенджер
     */
    public CoordinatesBuilderImpl(Messenger messenger){
        validator = new CoordinatesValidatorImpl();
        this.messenger = messenger;
    }

    @Override
    public void setX(Double x) throws InvalidFieldException {
        if (validator.validateCoordinatesX(x)) {
            this.x = x;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidCoordinatesX"));
        }
    }

    @Override
    public void setY(long y) throws InvalidFieldException {
        if (validator.validateCoordinatesY(y)) {
            this.y = y;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidCoordinatesY"));
        }
    }

    @Override
    public Coordinates getCoordinates(){
        return new Coordinates(x, y);
    }
}
