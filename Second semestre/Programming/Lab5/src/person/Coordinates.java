package person;

import exceptions.InvalidFieldException;

/**
 * Класс координат
 */
public class Coordinates {
    private Double x; //Максимальное значение поля: 882, Поле не может быть null
    private long y; //Значение поля должно быть больше -266

    /**
     * @param x координата x
     * @param y координата y
     */
    public Coordinates(Double x, long y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return координата x
     */
    public Double getX() {
        return x;
    }

    /**
     * @return координата y
     */
    public long getY() {
        return y;
    }
}
