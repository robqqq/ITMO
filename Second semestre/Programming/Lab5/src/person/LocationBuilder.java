package person;

import exceptions.InvalidFieldException;

/**
 * Интерфейс создателя локации
 */
public interface LocationBuilder {

    /**
     * Задать координату x  локации
     * @param x координата x локации
     */
    void setX(float x);

    /**
     * Задать координату y  локации
     * @param y координата y локации
     */
    void setY(long y);

    /**
     * Задать название локации
     * @param name название локации
     * @throws InvalidFieldException если значение некорректно
     */
    void setName(String name) throws InvalidFieldException;

    /**
     * @return объект Location
     */
    Location getLocation();
}
