package person;

import exceptions.InvalidCoordinatesXException;
import exceptions.InvalidCoordinatesYException;
import exceptions.InvalidFieldException;

/**
 * Реализация интерфеса CoordinatesBuilder
 */
public class CoordinatesBuilderImpl implements CoordinatesBuilder{
    private Double x; //Максимальное значение поля: 882, Поле не может быть null
    private long y; //Значение поля должно быть больше -266
    private final CoordinatesValidator validator;

    public CoordinatesBuilderImpl(){
        validator = new CoordinatesValidatorImpl();
    }

    @Override
    public void setX(Double x) throws InvalidCoordinatesXException {
        if (validator.validateCoordinatesX(x)) {
            this.x = x;
        } else {
            throw new InvalidCoordinatesXException();
        }
    }

    @Override
    public void setY(long y) throws InvalidCoordinatesYException {
        if (validator.validateCoordinatesY(y)) {
            this.y = y;
        } else {
            throw new InvalidCoordinatesYException();
        }
    }

    @Override
    public Coordinates getCoordinates(){
        return new Coordinates(x, y);
    }
}
