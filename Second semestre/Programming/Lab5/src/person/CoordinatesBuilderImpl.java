package person;

import exceptions.InvalidFieldException;
import messages.Messenger;

public class CoordinatesBuilderImpl implements CoordinatesBuilder{
    private Double x; //Максимальное значение поля: 882, Поле не может быть null
    private long y; //Значение поля должно быть больше -266
    private CoordinatesValidator validator;
    private Messenger messenger;

    public CoordinatesBuilderImpl(Messenger messenger){
        validator = new CoordinatesValidatorImpl();
        this.messenger = messenger;
    }

    /**
     * Метод, который устанавливают координату X
     * @param x
     * @throws InvalidFieldException
     */
    public void setX(Double x) throws InvalidFieldException {
        if (validator.validateCoordinatesX(x)) {
            this.x = x;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidCoordinatesX"));
        }
    }

    /**
     * Метод, который устанавливает координату Y
     * @param y
     * @throws InvalidFieldException
     */
    public void setY(long y) throws InvalidFieldException {
        if (validator.validateCoordinatesY(y)) {
            this.y = y;
        } else {
            throw new InvalidFieldException(messenger.getExceptionMsg("invalidCoordinatesY"));
        }
    }

    public Coordinates getCoordinates(){
        return new Coordinates(x, y);
    }
}
