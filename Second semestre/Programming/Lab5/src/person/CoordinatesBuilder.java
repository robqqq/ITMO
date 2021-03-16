package person;

import exceptions.InvalidFieldException;

/**
 * Интерфейс создателя координат
 */
public interface CoordinatesBuilder {

    /**
     * Задать координату x
     * @param x координата x
     * @throws InvalidFieldException если значение некорректно
     */
    void setX(Double x) throws InvalidFieldException;

    /**
     * Задать координату y
     * @param y координата y
     * @throws InvalidFieldException если значение некорректно
     */
    void setY(long y) throws InvalidFieldException;

    /**
     * @return объект Coordinates
     */
    Coordinates getCoordinates();
}
